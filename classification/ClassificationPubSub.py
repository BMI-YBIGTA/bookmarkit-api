#!/usr/bin/env python
# coding: utf-8

# In[16]:


#!pip3 install pika


# In[1]:


import pika
import requests
from bs4 import BeautifulSoup


# In[5]:


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
        bookmark_main_category = "AI"
        bookmark_sub_category = "DeepLearning"
        classified_publisher = BookmarkClassifiedEventPublisher()
        classified_publisher.publish(bookmark_id + '|||' + bookmark_content + '|||' + bookmark_main_category + '|||' + bookmark_sub_category)
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
    
    


# In[10]:


consumer = BookmarkRegisteredEventConsumer()
consumer.main()


# In[ ]:





# In[ ]:




