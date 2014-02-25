import socket
import sys
import time

#def get_free_port():
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(('',9999))
#port = s.getsockname()
s.listen(1)
while True:
    print "Waiting on Client"
    sc, address = s.accept()   
    print "Connected to ", address    
    f = open("copy.1G",'wb') 	   
    l = sc.recv(1024)
    localtime = time.asctime( time.localtime(time.time()) )
    print "Start time :", localtime
    while (l):
        f.write(l)
        l = sc.recv(1024)
    f.close()
    localtime = time.asctime( time.localtime(time.time()) )
    print "End time :", localtime

    sc.close()

print "Server DONE"
s.close()
#return port
