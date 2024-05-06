# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
ENV['VAGRANT_SERVER_URL'] = 'https://vagrant.elab.pro'

VAGRANTFILE_API_VERSION = "2"

nodes = {
    "client01" => { :ip => "192.168.56.20", :cpus => "2", :mem => "2048"},
}

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
    config.vm.box = "ubuntu/bionic64"

    config.vm.boot_timeout = 600

    config.vm.provision "file", source: "~/.ssh/id_rsa.pub", destination: "/home/vagrant/.ssh/id_rsa.pub"
    config.vm.provision "shell", inline: "cat /home/vagrant/.ssh/id_rsa.pub >> /home/vagrant/.ssh/authorized_keys"

    nodes.each_with_index do |(hostname, cfg), index|
        config.vm.define hostname do |node|
            node.vm.provider "virtualbox" do |v|
                v.cpus = cfg[:cpus]
                v.memory = cfg[:mem]
                v.auto_nat_dns_proxy = false
                v.customize ["modifyvm", :id, "--natdnsproxy1", "off"]
                v.customize ["modifyvm", :id, "--natdnshostresolver1", "off"]
                v.customize ["modifyvm", :id, "--audio", "none"]
                v.customize ["modifyvm", :id, "--pae", "off"]
            end
            node.vm.network "private_network", ip: cfg[:ip]

            if hostname == "client01"
              node.vm.provision 'ansible' do |ansible|
                ansible.playbook = "playbook.yml"
                ansible.verbose = true
                ansible.inventory_path = "inventory"
                ansible.limit = "all"
                ansible.become = true
                ansible.galaxy_role_file = 'requirements.yml'
                ansible.galaxy_command = 'ansible-galaxy install --ignore-errors --force --role-file=%{role_file}'
              end
            end
        end
    end
end
