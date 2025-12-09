import pytest

@pytest.fixture
def input_value():
   input = 39
   return input

@pytest.fixture
def number_list():
    return list(range(11))