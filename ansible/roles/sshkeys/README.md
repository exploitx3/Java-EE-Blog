Role Name
=========

Put any required SSH keys into the authorized_keys file of the specified user.

Requirements
------------

File is assumed to be the same name as the user ID plus '.pub' and exist in the files/ subdirectory. e.g. ubuntu user requires ubuntu.pub file to exist in files/. You can, if you choose, specify a full path to the file too, since the key is supplied within the task using a 'lookup'. By default the key will be pushed to the remote 'ubuntu' user, this is defined in defaults. You can override this in the dict with the user: key.

Role Variables
--------------

    sshkeys_user: ubuntu
    sshkeys_users:

Dependencies
------------


Example Playbook
----------------

    - hosts: servers
      roles:
         - { role: sshkeys, sshkeys_users: [ubuntu,root] }
