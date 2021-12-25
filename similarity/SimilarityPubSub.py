#!/usr/bin/env python
# coding: utf-8

# In[2]:


import pika


# In[3]:


class SimilarityRegisteredEventConsumer:
    def __init__(self):
        self.__url = 'localhost'
        self.__port = 5672
        self.__vhost = '/'
        self.__cred = pika.PlainCredentials('mq', 'secret')
        self.__queue = 'q.similarity.registered'
        return
    
    def on_message(channel, method_frame, header_frame, body):
        print('Received %s' % body)
        input_link = body.decode('utf-8')
        output_1 = "output_link"
        output_2 = "output_list2"
        output_3 = "output_link3"
        classified_publisher = SimilarityAnalyzedPublisher()
        classified_publisher.publish(input_link + '|||' + output_link1 + '|||' + output_link2 + '|||' + output_link3)
        return
    
    def main(self):
        conn = pika.BlockingConnection(pika.ConnectionParameters(self.__url, self.__port, self.__vhost, self.__cred))
        chan = conn.channel()
        chan.basic_consume(
            queue = self.__queue,
            on_message_callback = SimilarityRegisteredEventConsumer.on_message,
            auto_ack = True
        )
        print('SimilarityRegisteredEventConsumer is Starting...')
        chan.start_consuming()
        return


# In[4]:


class SimilarityAnalyzedEventPublisher:
    def __init__(self):
        self.__url = 'localhost'
        self.__port = 5672
        self.__vhost = '/'
        self.__cred = pika.PlainCredentials('mq', 'secret')
        self.__queue = 'q.similarity.analyzed'
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
    
    


# In[7]:


consumer = SimilarityRegisteredEventConsumer()
consumer.main()


# In[ ]:




