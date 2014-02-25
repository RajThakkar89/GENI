import socket
import sys
import time

s = socket.socket()
s.connect(("localhost",9999))
s.gethostbyname(hostname)
input_var = raw_input("Enter file name: ")
localtime = time.asctime( time.localtime(time.time()) )
print "Start time :", localtime
f=open (input_var, "rb") 
l = f.read(1024)
while (l):
    s.send(l)
    l = f.read(1024)
localtime = time.asctime( time.localtime(time.time()) )
print "End time :", localtime
s.close()

