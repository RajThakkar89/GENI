echo "Hello $USER"
#echo "Today is \c ";date
echo "Number of user login : \c" ; who | wc -l
#echo "Calendar"
#cal
sudo apt-get update
sudo apt-get install openjdk-6-jdk
sudo apt-get install iperf
sudo apt-get install build-essential
sudo wget http://jaist.dl.sourceforge.net/project/iperf/iperf-2.0.5.tar.gz
sudo tar zxvf iperf-2.0.5.tar.gz
cd iperf-2.0.5
sudo ./configure
sudo make
sudo make install clean
exit 0
