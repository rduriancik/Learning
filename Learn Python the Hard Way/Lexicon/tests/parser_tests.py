from nose.tools import *
from Lexicon.parser import *


def test_parse_verb():
    word = parse_verb([('stop', 'the'), ('stop', 'from'), ('verb', 'run')])
    assert_equal(('verb', 'run'), word)
    assert_raises(ParserError, parse_verb, [('direction', 'north'), ('verb', 'run')])


def test_parse_object():
    word = parse_object([('stop', 'the'), ('stop', 'from'), ('direction', 'north')])
    assert_equal(('direction', 'north'), word)
    assert_raises(ParserError, parse_object, [('verb', 'run'), ('direction', 'north')])


def test_parse_subject():
    word = parse_subject([('stop', 'the'), ('stop', 'from'), ('noun', 'door')])
    assert_equal(('noun', 'door'), word)
    word = parse_subject([('stop', 'the'), ('stop', 'from'), ('verb', 'run')])
    assert_equals(('noun', 'player'), word)
    assert_raises(ParserError, parse_subject, [('direction', 'north'), ('noun', 'princess')])


def test_parser_sentence():
    sentence = parse_sentence([('stop', 'the'), ('noun', 'princess'), ('verb', 'runs'), ('stop', 'from'),
                              ('stop', 'the'), ('direction', 'north')])
    assert_equals(sentence.subject,  'princess')
    assert_equals(sentence.verb,'runs')
    assert_equals(sentence.object, 'north')
