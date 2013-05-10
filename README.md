# Git, Bitbucket/Github setup

1. [Download and install Git.](http://git-scm.com)
    Also, [TortoiseGit on Windows.](http://code.google.com/p/tortoisegit/)

    During the install, just click Next,Continue or Finish, except select `Run Git and included Unix tools from the Windows Command Prompt` during the Git install.
2. Configure Git. (Do this in Git Bash: `Start -> All Programs -> Git -> Git Bash` on Windows, `Terminal` elsewhere)

    ```bash
git config --global user.name "Joey Lawrance"
git config --global user.email lawrancej@wit.edu
ssh-keygen -t rsa # Just press enter until it's done
```

3. Set up a [Bitbucket](http://bitbucket.org) or [Github](http://github.com) account. Use your `@wit.edu` email address. If using Github, [you must request private repositories.](http://github.com/edu)
4. [Email me your user name and tell me which service you're using.](lawrancej@wit.edu)
5. Clone (don't fork) this repository.

    ```bash
git clone https://bitbucket.org/lawrancej/compilers.git
cd compilers
```

    When you press enter, you should see something like this:
    ```bash
Cloning into 'compilers'...
remote: Counting objects: 12, done.
remote: Compressing objects: 100% (10/10), done.
emote: Total 12 (delta 1), reused 0 (delta 0)
Unpacking objects: 100% (12/12), done.
```

6. Create a new *private* repository called `Compilers` on bitbucket or github.
