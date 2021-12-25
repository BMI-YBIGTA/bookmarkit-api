#!/usr/bin/env python
# coding: utf-8

# In[16]:


#!pip3 install pika


# In[7]:


#!pip3 install torch


# In[8]:


import pika
import requests
from bs4 import BeautifulSoup
import torch
from torch import nn
import torch.nn.functional as F
import torch.optim as optim
from torch.utils.data import Dataset, DataLoader
import gluonnlp as nlp
import numpy as np
from tqdm import tqdm, tqdm_notebook
import pandas as pd

# kobert
from kobert.utils import get_tokenizer
from kobert.pytorch_kobert import get_pytorch_kobert_model


# In[11]:


df = pd.read_csv("final_category.csv")


# In[2]:


class BookmarkRegisteredEventConsumer:
    def __init__(self):
        self.__url = 'localhost'
        self.__port = 5672
        self.__vhost = '/'
        self.__cred = pika.PlainCredentials('mq', 'secret')
        self.__queue = 'q.classification.registered'
        return
    
    def on_message(channel, method_frame, header_frame, body):
        print('Received %s' % body)
        splitted = body.decode('utf-8').split('|||')
        bookmark_id = splitted[0]
        bookmark_link = splitted[1]
        print('bookmark_id %s' % bookmark_id)
        print('bookmark_link %s' % bookmark_link)
        webpage = requests.get(bookmark_link)
        soup = BeautifulSoup(webpage.content, "html.parser")
        bookmark_content = soup.body.text
        print('bookmark_content %s' % bookmark_content)
        result = predict(bookmark_content)
        print('result %s ' % result)
        bookmark_main_category = "AI"
        bookmark_sub_category = "DeepLearning"
        classified_publisher = BookmarkClassifiedEventPublisher()
        classified_publisher.publish(bookmark_id + '|||' + bookmark_content + '|||' + result)
        return
    
    def main(self):
        conn = pika.BlockingConnection(pika.ConnectionParameters(self.__url, self.__port, self.__vhost, self.__cred))
        chan = conn.channel()
        chan.basic_consume(
            queue = self.__queue,
            on_message_callback = BookmarkRegisteredEventConsumer.on_message,
            auto_ack = True
        )
        print('BookmarkRegisteredEventConsumer is Starting...')
        chan.start_consuming()
        return


# In[3]:


class BookmarkClassifiedEventPublisher:
    def __init__(self):
        self.__url = 'localhost'
        self.__port = 5672
        self.__vhost = '/'
        self.__cred = pika.PlainCredentials('mq', 'secret')
        self.__queue = 'q.classification.classified'
        return
    
    def publish(self, body):
        conn = pika.BlockingConnection(pika.ConnectionParameters(self.__url, self.__port, self.__vhost, self.__cred))
        chan = conn.channel()
        chan.basic_publish(
            exchange = '',
            routing_key = self.__queue,
            body = body
        )
        conn.close()
        return
    
    


# In[ ]:


# torch
import torch
from torch import nn
import torch.nn.functional as F
import torch.optim as optim
from torch.utils.data import Dataset, DataLoader
import gluonnlp as nlp
import numpy as np
from tqdm import tqdm, tqdm_notebook

# kobert
from kobert.utils import get_tokenizer
from kobert.pytorch_kobert import get_pytorch_kobert_model

# GPU 사용
device = torch.device("cpu")

# BERT 모델, Vocabulary 불러오기 필수
bertmodel, vocab = get_pytorch_kobert_model()

# KoBERT에 입력될 데이터셋 정리


class BERTDataset(Dataset):
    def __init__(self, dataset, sent_idx, label_idx, bert_tokenizer, max_len,
                 pad, pair):
        transform = nlp.data.BERTSentenceTransform(
            bert_tokenizer, max_seq_length=max_len, pad=pad, pair=pair)

        self.sentences = [transform([i[sent_idx]]) for i in dataset]
        self.labels = [np.int32(i[label_idx]) for i in dataset]

    def __getitem__(self, i):
        return (self.sentences[i] + (self.labels[i], ))

    def __len__(self):
        return (len(self.labels))

# 모델 정의


class BERTClassifier(nn.Module):  # 클래스를 상속
    def __init__(self,
                 bert,
                 hidden_size=768,
                 num_classes=93,  # 클래스 수 조정##
                 dr_rate=None,
                 params=None):
        super(BERTClassifier, self).__init__()
        self.bert = bert
        self.dr_rate = dr_rate

        self.classifier = nn.Linear(hidden_size, num_classes)
        if dr_rate:
            self.dropout = nn.Dropout(p=dr_rate)

    def gen_attention_mask(self, token_ids, valid_length):
        attention_mask = torch.zeros_like(token_ids)
        for i, v in enumerate(valid_length):
            attention_mask[i][:v] = 1
        return attention_mask.float()

    def forward(self, token_ids, valid_length, segment_ids):
        attention_mask = self.gen_attention_mask(token_ids, valid_length)

        _, pooler = self.bert(input_ids=token_ids, token_type_ids=segment_ids.long(
        ), attention_mask=attention_mask.float().to(token_ids.device))
        if self.dr_rate:
            out = self.dropout(pooler)
        return self.classifier(out)


# Setting parameters
max_len = 512
batch_size = 4
warmup_ratio = 0.1
num_epochs = 10
max_grad_norm = 1
log_interval = 200
learning_rate = 2e-5

# 학습 모델 로드
MODEL_LOAD_PATH = './'
model = torch.load(MODEL_LOAD_PATH + 'model_epoch5.pt',
                   map_location=device)  # 전체 모델을 통째로 불러옴, 클래스 선언 필수
model.load_state_dict(torch.load(MODEL_LOAD_PATH + 'model_state_dict_epoch5.pt',
                                 map_location=device))  # state_dict를 불러 온 후, 모델에 저장

# 토큰화
tokenizer = get_tokenizer()
tok = nlp.data.BERTSPTokenizer(tokenizer, vocab, lower=False)


def new_softmax(a):
    c = np.max(a)  # 최댓값
    exp_a = np.exp(a-c)  # 각각의 원소에 최댓값을 뺀 값에 exp를 취한다. (이를 통해 overflow 방지)
    sum_exp_a = np.sum(exp_a)
    y = (exp_a / sum_exp_a) * 100
    return np.round(y, 3)

# 예측 모델 설정

def predict(predict_sentence):

    abclist = ['ASP', 'AWS', 'Algorithm', 'Android', 'Android Studio', 'Angular', 'Apache', 'Authentication', 'Beautiful Soup', 'Blockchain', 'C', 'CSS', 'DNS', 'Dart', 'Data Structure', 'Deep Learning', 'Django', 'Docker', 'Eclipse',
               'Elasticsearch', 'Flask', 'Flutter', 'GCP', 'Git', 'GitLab', 'Github Action',
               'Go', 'GraphQL', 'HTML', 'HTTP', 'Hadoop', 'Haskell', 'IntelliJ', 'Java',
               'JavaScript', 'Jenkins', 'Julia', 'Jupyter Notebook', 'Kafka', 'Kotlin',
               'Kubernetes', 'Laravel', 'MATLAB', 'Machine Learning', 'NestJS', 'Network',
               'NextJS', 'Nginx', 'NoSQL', 'Node.js', 'Object Oriented Programming',
               'Objective-C', 'Operating System', 'PHP', 'Perl', 'PyCharm', 'Python', 'R',
               'RDB', 'REST', 'RabbitMQ', 'Raspberry Pi', 'React', 'React Native', 'Redux',
               'Reinforcement Learning', 'Ruby', 'Rust', 'SOAP', 'SQL', 'SVN', 'Sass', 'Scala',
               'Security', 'Selenium', 'Software Engineering', 'Spark', 'Spring', 'Swift',
               'Terraform', 'Tomcat', 'Travis CI', 'TypeScript', 'Unity', 'Visual Studio',
               'Visual Studio Code', 'Vue.js', 'Xcode', 'Zookeeper', 'gRPC', 'iOS', 'jQuery',
               'webpack']

    data = [predict_sentence, '0']
    dataset_another = [data]

    another_test = BERTDataset(
        dataset_another, 0, 1, tok, max_len, True, False)
    test_dataloader = torch.utils.data.DataLoader(
        another_test, batch_size=1)  # num_workers=5

    model.eval()

    for batch_id, (token_ids, valid_length, segment_ids, label) in enumerate(test_dataloader):
        token_ids = token_ids.long().to(device)
        segment_ids = segment_ids.long().to(device)

        valid_length = valid_length
        label = label.long().to(device)

        out = model(token_ids, valid_length, segment_ids)

        test_eval = []
        for i in out:
            logits = i
            logits = logits.detach().cpu().numpy()
            min_v = min(logits)
            total = 0
            answerlist = []
            logits = np.round(new_softmax(logits), 3).tolist()
            for logit in logits:
                answerlist.append(np.round(logit, 3))

            answer = abclist[np.argmax(logits)]

            answerlist.append(answer)
            
        sub_category = answerlist[-1]
        main_category = df[df['sub_category'] == sub_category].main_category
    return sub_category + '|||' + main_category


# In[4]:


consumer = BookmarkRegisteredEventConsumer()
consumer.main()


# In[ ]:





# In[ ]:




