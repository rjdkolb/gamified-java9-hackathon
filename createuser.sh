for userdetails in `cat //home/ubuntu/users.csv`
do
        user=`echo $userdetails | cut -f 1 -d ,`
        passwd=`echo $userdetails | cut -f 2 -d ,`
        echo "useradd -d /home/$user -p `mkpasswd $passwd` $user"
        echo "mkdir /home/$user"
        echo "cp /opt/.profile /home/$user/"
        echo "chown -R $user:users /home/$user"
        echo "usermod -s /bin/bash $user"
done

