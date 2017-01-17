try:
    from setuptools import setup
except ImportError:
    from distutils.core import setup


config = {
    'description': 'Ex47',
    'author': 'Robert Duriancik',
    'url': 'URL to get it at',
    'download_url': 'Where to download it.',
    'author_email': 'r.duriancik@gmail.com',
    'version': '0.1',
    'install_requires': ['nose'],
    'packages': [],
    'scripts': [],
    'name': 'ex47'
}

setup(**config)