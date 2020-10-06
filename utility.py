# BASE URL
BASE_URL = 'https://swapi.dev/api/'
# FILMS ENPOINT
FILMS_ENDPOINT = 'films/'
# FILMS URL
FILMS_URL = BASE_URL+FILMS_ENDPOINT


# Show the main menu
def print_options():
    print(
        '\n***********************************'
        '\n1- Show all movies\n2- Exit \n'
        '***********************************\n'
    )


# Show film menu
def print_films_options(films):
    print(
        '\n***********************************'
        '\nChoose film: \n'
    )
    _print_films_options(films)
    print('***********************************\n')
    pass


# Show films menu helper function to show each movie
def _print_films_options(films):
    for i in range(0, len(films)+1):
        # Back to menu option
        if i == len(films):
            print('{index}- Back to films menu\n'.format(index=i+1))
            return

        # Default show the number of the film and its title
        print(
            '{index}- {title}\n'.format(index=i+1, title=films[i]['title'])
        )


# Show people menu
def print_people_options(characters):
    for i in range(0, len(characters)+1):
        # Back to menu option
        if i == len(characters):
            print('\n\n{index}- Back to characters menu\n'.format(index=i+1))
            return

        if i % 3 == 0:
            print('')

        # Default show the number of the character and his name
        print(
            '{index}- {name}'.format(index=i+1, name=characters[i]['name']),
            end=' | '
        )


# Show error message
def show_error_message(e):
    print(
        '\n***********************************'
        '\nError: {error}\n'
        '***********************************\n'
        .format(error=e)
    )


# Show wrong choice message
def show_wrong_choice_message():
    print(
        '\n***********************************'
        '\nWrong Choice! \nplease choose another choice \n'
        '***********************************\n'
    )
