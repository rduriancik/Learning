try:
    from setuptools import setup
except ImportError:
    from distutils.core import setup


config = {
    'description': 'Example project',
    'author': 'Robert Duriancik',
    'url': 'URL to get it at',
    'download_url': 'Where to download it.',
    'author_email': 'r.duriancik@gmail.com',
    'version': '0.1',
    'install_requires': ['nose'],
    'packages': ['NAME'],
    'scripts': ['bin/example.py'],
    'name': 'example'
}

setup(**config)