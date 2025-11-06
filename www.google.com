nano dns.txt

193.51.31.90 www.uvsq.fr
193.51.25.12 ecampus.uvsq.fr
193.51.31.154 poste.uvsq.fr
192.168.1.1 www.google.com
192.168.1.2 www.facebook.com


java -cp target/classes fr.uvsq.cprog.dns.DnsApp

> www.google.com
> 192.168.1.2
> ls -a uvsq.fr



quit

quit 
192.168.1.1


