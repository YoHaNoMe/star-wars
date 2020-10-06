# Star Wars
Application that should allow the user to find all the characters/people in a specific Star Wars movie
Topics Resources.


## Getting Started

### Installing Dependencies

#### Python
Follow instructions to install the latest version of python for your platform in the Python [Docs](https://docs.python.org/3/using/unix.html#getting-and-installing-the-latest-version-of-python)

#### Virtual Environment
I recommend working within a virtual environment whenever using Python for projects. This keeps your dependencies for each project separated and organized. Instructions for setting up a virtual environment for your platform can be found in the [Python Docs](https://packaging.python.org/guides/installing-using-pip-and-virtual-environments/).
If you don't want to read the [Python Docs](https://packaging.python.org/guides/installing-using-pip-and-virtual-environments/), you could install virtual environment by:

on macOS and Linux
```
python3 -m pip install --user virtualenv
```
on Windows
```
py -m pip install --user virtualenv
```
then you have to create the virtual environment by:

On macOS and Linux
```
python3 -m venv env
```
On Windows
```
py -m venv env
```
finally you have to ***Activate*** the virtual environment by:

On macOS and Linux
```
source env/bin/activate
```
On Windows
```
.\env\Scripts\activate
```
You can confirm you’re in the virtual environment by checking the location of your Python interpreter, it should point to the *env* directory.

On macOS and Linux
```
which python
```
On Windows
```
where python
```
You could leave the virtual environment by:
```
deactivate
```

#### PIP Dependencies
Once you have your virtual environment setup and running, install dependencies by making **sure** you are in the right folder for the project. then, running:
```
pip install -r requirements.txt
```
This will install all of the required packages within the `requirements.txt` file.


## Running the application
```
python3 main.py
```
