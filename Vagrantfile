# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.hostmanager.enabled = true
  config.hostmanager.manage_host = true
  config.hostmanager.manage_guest = true
  config.hostmanager.ignore_private_ip = false
  config.hostmanager.include_offline = true

  config.vm.box   = "debian/stretch64"
  config.vm.network "private_network", ip: "192.168.33.61"
  config.vm.hostname = "sp.local"
  config.vm.synced_folder ".", "/var/www/", type: "nfs", mount_options: ['rw', 'vers=3', 'tcp', 'fsc' ,'actimeo=2']
  config.vm.provider "virtualbox" do |v|
    v.name = "sp.local"
    v.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
    v.customize ["modifyvm", :id, "--memory", 1024]
    v.customize ["modifyvm", :id, "--name", "sp.local"]
    v.cpus = 2
end
end