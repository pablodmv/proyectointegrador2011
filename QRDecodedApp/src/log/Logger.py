'''
Created on 12/11/2011

@author: gustavo
'''
import os
import logging

class Logger(object):
    '''
    classdocs
    '''
    #os.getcwd()
    ARCHIVO_LOG = "/home/gustavo/workspace/qrApp.log"
    logging.basicConfig(format='%(asctime)s %(message)s', datefmt='%m/%d/%Y %I:%M:%S %p',filename=ARCHIVO_LOG,level=logging.INFO)

    def __init__(self):
        '''
        Constructor
        '''
        
    @classmethod    
    def info(self,msg):
        logging.info(msg)
    