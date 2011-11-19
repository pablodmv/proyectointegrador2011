'''
Created on 12/11/2011

@author: gustavo
'''

if __name__ == '__main__':
    f = open("/home/gustavo/workspace/QRDecodedApp/store/store.txt")
    while True:
        line = f.readline()
        if not line: break
        print ">" + line
            
    f.close()