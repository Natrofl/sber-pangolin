# Task 1

Before we start, please ensure you have Java installed, as Groovy runs on the Java platform (JVM).

## Installing Groovy

### For Windows

1. You can download Groovy from the official website [Groovy Download Page](https://groovy-lang.org/download.html)
2. After downloading, follow the installation instructions.

### For MacOS

1. Using Homebrew, you can install Groovy by simply running the following command in your terminal:

```bash
brew install groovy
```

### For Linux (Ubuntu)
1. Run these commands to install Groovy:
```bash
sudo snap install groovy --classic
```

### Running Groovy Code
```bash
groovy compression_func.groovy
```
That's it! If you have correctly installed Groovy, you should be able to execute Groovy scripts from the command line.

# Task 2

Before we start, please ensure you have python3-venv and Vagrant installed on your Linux OS.

## Create virtualenv
1. Run these commands to create virturalenv
```bash
python3 -m venv venv
```

## Activate venv and install necessary packages
1. Run these commands
```bash
source ./venv/bin/activate
pip install -r requirements.txt
```

## Testing
1. Run these command
```bash
vagrant up
```

### Example Playbook
```YAML
---
- hosts: all
  become: true
  vars:
  tasks:
    - ansible.builtin.assert:
        that: "ansible_os_family == 'Debian'"
        fail_msg: "OS family incorrect {{ ansible_os_family }} != Debian"

    - name: Adding administrative user
      ansible.builtin.user:
        name: user
        password: "{{ 'qweASD!@#' | string | password_hash('sha512', 'secretsaltpangol') }}"
        groups: sudo
        shell: /bin/bash
        create_home: true
        append: true

    - name: Allow the user to sudo
      community.general.sudoers:
        name: user
        state: present
        user: user
        nopassword: true
        commands: ALL
```

That's it!

# Task 3

Before we start, please ensure you have python3-venv and Vagrant installed on your Linux OS.

## Create virtualenv
1. Run these commands to create virturalenv
```bash
python3 -m venv venv
```

## Activate venv and install necessary packages
1. Run these commands
```bash
source ./venv/bin/activate
pip install -r requirements.txt
```

## Run j2-ansible compressor
1. Run these commands
```bash
ansible-playbook playbook.yml
```
