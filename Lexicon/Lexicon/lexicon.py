_lexicon = {
    'north': 'direction',
    'south': 'direction',
    'east': 'direction',
    'west': 'direction',
    'down': 'direction',
    'up': 'direction',
    'left': 'direction',
    'right': 'direction',
    'back': 'direction',
    'go': 'verb',
    'stop': 'verb',
    'kill': 'verb',
    'eat': 'verb',
    'the': 'stop',
    'in': 'stop',
    'of': 'stop',
    'from': 'stop',
    'at': 'stop',
    'it': 'stop',
    'door': 'noun',
    'bear': 'noun',
    'princess': 'noun',
    'cabinet': 'noun',
}


def is_number(number):
    """Returns True if the argument is number, False otherwise """
    try:
        int(number)
        return True
    except ValueError:
        return False


def analyze_word(word):
    """Analyzes a word and returns a tuple in form (word_type, word) """
    word_copy = word.lower()
    if _lexicon.has_key(word_copy):
        return tuple([_lexicon.get(word_copy), word])
    elif is_number(word_copy):
        return tuple(['number', int(word)])
    else:
        return tuple(['error', word])


def scan(text):
    """Scans text and returns list with tuples of word types and the word """
    words = text.split(' ')
    word_list = []
    for word in words:
        word_list.append(analyze_word(word))

    return word_list
