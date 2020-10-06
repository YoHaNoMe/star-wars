import requests
import re
from utility import *


# Show character
def show_character(films, character):
    # Get a list of films indexes
    films_indexes = re.findall(r'\d+', ''.join(character['films']))
    '''
    Get a string of films titles
    by using films_indexes
    '''
    films_titles = ', '.join(
        [films[int(film_index)-1]['title'] for film_index in films_indexes]
    )
    print(
        '\n***********************************\n'
        'name: {name}\n'
        'Mass: {mass}\n'
        'Hair color: {hair_color}\n'
        'Skin color: {skin_color}\n'
        'Eye color: {eye_color}\n'
        'Birth year: {birth_year}\n'
        'Gender: {gender}\n'
        'Films: {films}\n'
        '***********************************\n'
        .format(
            name=character['name'],
            mass=character['mass'],
            hair_color=character['hair_color'],
            skin_color=character['skin_color'],
            eye_color=character['eye_color'],
            birth_year=character['birth_year'],
            gender=character['gender'],
            films=films_titles
        )
    )


# Show film
def show_film(films, index):
    print('\nLoading, please wait....\n')
    # Get characters urls
    characters_url = films[index]['characters']
    # Characters list
    characters = []
    # Request all characters in certain film
    for i in range(0, len(characters_url)):
        try:
            character = requests.get(
                characters_url[i].replace('http', 'https')).json()
            characters.append(character)
        except Exception as e:
            show_error_message(e)

    while True:
        # Show all the people in certain film
        print_people_options(characters)
        # Get user input
        try:
            user_input = int(input('Enter your choice: '))
        except ValueError as e:
            show_error_message(e)
        '''
        check if the user enter a correct number
        then show the character if the input is correct
        '''
        if user_input <= len(characters) and user_input > 0:
            show_character(films, characters[user_input-1])
        # Back to the films menu
        elif user_input == len(characters)+1:
            break
        # User enter wrong number
        else:
            show_wrong_choice_message()
            continue


# Show all films
def show_all_films():
    print('\nLoading, please wait....\n')
    # Request all films
    try:
        films_data = requests.get(FILMS_URL).json()
    except Exception as e:
        show_error_message(e)

    while True:
        # Get films
        films = films_data['results']
        # Show films menu
        print_films_options(films)
        # Get user input
        try:
            user_input = int(input('Enter your choice: '))
        except ValueError as e:
            show_error_message(e)
        '''
        check if the user enter a correct number
        then show the films if the input is correct
        '''
        if user_input <= len(films) and user_input > 0:
            show_film(films, user_input-1)
        # Back to the main menu
        elif user_input == len(films)+1:
            break
        # User enter wrong number
        else:
            show_wrong_choice_message()
            continue


'''

Main Program

'''
if __name__ == '__main__':
    while True:
        # Show the main menu
        print_options()
        '''
        Try to get the user input
        if there is any error (i.e user entered string), show an error message
        '''
        try:
            user_input = int(input('Enter your choice(1, 2): '))
        except ValueError as e:
            show_error_message(e)
        # show all movies choice
        if user_input == 1:
            show_all_films()
        # exit chocie
        elif user_input == 2:
            print('\nGood bye...\n')
            break
        # wrong choice
        else:
            show_wrong_choice_message()
            continue
