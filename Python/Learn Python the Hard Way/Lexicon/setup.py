try:
    from setuptools import setup
except ImportError:
    from distutils.core import setup


config = {
    'description': 'Lexicon parses the user input',
    'author': 'Robert Duriancik',
    'url': 'URL to get it at',
    'download_url': 'Where to download it.',
    'author_email': 'r.duriancik@gmail.com',
    'version': '0.1',
    'install_requires': ['nose'],
    'packages': ['Lexicon'],
    'scripts': ['Lexicon/lexicon.py'],
    'name': 'Lexicon'
}

setup(**config)