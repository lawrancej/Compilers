# Git, Bitbucket/Github setup

1. [Download and install Git.](http://git-scm.com)
    (Also, [TortoiseGit on Windows.](http://code.google.com/p/tortoisegit/))

    During installation, always click *Next*, *Continue* or *Finish*, but in the Git install, be sure to first select **Run Git and included Unix tools from the Windows Command Prompt** when it is an option.

2. Configure Git.

	In Windows, open Git Bash: **Start -> All Programs -> Git -> Git Bash**. In Linux/Mac OSX, open **Terminal**.

    ```bash
git config --global user.name "Joey Lawrance"    # Use your name
git config --global user.email lawrancej@wit.edu # Use your email
ssh-keygen -t rsa # Just press enter until it's done
```

3. Create a [Bitbucket](http://bitbucket.org) or [Github](http://github.com) account. Use your *@wit.edu* email address. 

    **Note:** Bitbucket will automatically give you private repositories necessary for submitting work. If you use Github, [you must first request private repositories.](http://github.com/edu)

4. Create a new **private** repository called **Compilers** on [Bitbucket](https://bitbucket.org/repo/create) or [Github](https://github.com/new).

5. [Email me your user name and tell me which service you're using.](mailto:lawrancej@wit.edu)

6. Clone (**do not fork**) this repository.

    ```bash
git clone https://bitbucket.org/lawrancej/compilers.git
 - or - 
git clone https://github.com/lawrancej/Compilers.git
```

    When you press enter, you should see something like this:

    ```bash
Cloning into 'compilers'...
remote: Counting objects: 12, done.
remote: Compressing objects: 100% (10/10), done.
emote: Total 12 (delta 1), reused 0 (delta 0)
Unpacking objects: 100% (12/12), done.
```

7. Go into the repository you just cloned.

    ```bash
cd compilers
```

8. Connect the repository to your private repository.

	Copy the *.git* URL from Bitbucket or Github.

    ```bash
git remote -v
git remote add me url.to.private.repo.goes.here.git
git push -u me master
```

9. Reload your private repository to verify everything pushed over properly.
