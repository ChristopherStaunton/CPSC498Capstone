from bs4 import BeautifulSoup
import requests
from textblob import TextBlob

# Global Variables
# list of results
results = []
# which search methods are available
searchA = True
searchB = True
searchC = True


# Generic google search
# unstable / unpredictable / numerous
def searchMethodA(topic):
    global results
    USER_AGENT = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36'}
    response = requests.get('https://www.google.com/search?q={}&num={}&hl={}'.format(topic, 100, 'en'), headers=USER_AGENT)
    response.raise_for_status()
    soup = BeautifulSoup(response.text, 'html.parser')
    results = []
    result_block = soup.find_all('div', attrs={'class': 'g'})
    for a in result_block:
        link = a.find('a', href=True)
        title = a.find('h3')
        description = a.find('span', attrs={'class': 'st'})
        if link and title:
            link = link['href']
            title = title.get_text()
            if description:
                description = description.get_text()
            if link != '#':
                results.append({topic, title, description})


# Google search
# Collects related titles at top of search
# Sometimes works well depending on input
# unstable / unreliable / inconsistent
def searchMethodB(topic):
    USER_AGENT = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36'}
    response = requests.get('https://www.google.com/search?q={}&num={}&hl={}'.format(topic, 100, 'en'), headers=USER_AGENT)
    response.raise_for_status()
    soup = BeautifulSoup(response.text, 'html.parser')
    scrape = soup.find_all('div', attrs={'class': 'kltat'})
    for a in scrape:
        results.append(a.find('span'))


# Database of popular short crossword puzzle words based on year
# Results available for years 1942 - 2020
# First test search method
# Intended as a extra side search method
# stable / reliable / limited
# https://www.xwordinfo.com/popular
def searchMethodC(year):
    global results
    if not (1942 <= int(year) <= 2020):
        print()  # do nothing
    else:
        page = requests.get("https://www.xwordinfo.com/Popular?year=" + str(year))
        soup = BeautifulSoup(page.content, 'html.parser')
        table = soup.table
        tableRows = table.find_all('tr')
        for row in tableRows:
            t = row.find_all('td')
            info = [i.text for i in t]
            results.append(info)


# Search a web site that finds related words
# Still being worked on
# Issues with flexbox
# stable / effective / numerous
# https://relatedwords.org/
# def searchMethodD(topic):
#    global results
#    results = []
#    page = requests.get('https://relatedwords.org/relatedto/' + topic)
#    soup = BeautifulSoup(page.content, 'html.parser')
#    result = soup.find_all('a.flexbox', attrs={'class': "item"})
#    for result in result_block:
#        print(result)


# Determines if results are sufficient
# Currently only return true for testing purposes
# Incomplete
def areResultsGood():
    return True


# Prints current results
def printResults():
    for a in results:
        print(a)


# Clean results array of extra unnecessary characters
# Incomplete
# Waiting until search methods are complete
def cleanResults():
    print()


# Selects next search method
def search(sTopic, sType, sInput):
    global searchA
    global searchB
    global searchC
    resultsFound = False
    if sType == 'a' and searchA:
        searchMethodA(sTopic)
        resultsFound = areResultsGood()
        searchA = False
    elif sType == 'b' and searchB:
        searchMethodB(sTopic)
        resultsFound = areResultsGood()
        searchB = False
    elif sType == 'c' and searchC:
        searchMethodC(sInput)
        resultsFound = areResultsGood()
        searchC = False
    elif searchA:
        searchMethodA(sTopic)
        resultsFound = areResultsGood()
        searchA = False
    elif searchB:
        searchMethodB(sTopic)
        resultsFound = areResultsGood()
        searchB = False
    elif searchC:
        searchMethodC(sInput)
        resultsFound = areResultsGood()
        searchC = False
    if (not searchA and not searchB and not searchC) or resultsFound:
        return True
    else:
        return False


# Start of program
def programStart(searchTopic, searchType, searchInput):
    searchesDone = False
    while not searchesDone:
        searchesDone = search(searchTopic, searchType, searchInput)


# Tests
# programStart("pizza", 'a', 0)
# programStart("tv", 'a', 0)
# programStart("movies", 'b', 0)
# programStart("xbox games", 'b', 0)
# programStart("irrelevant", 'c', 2006)
# printResults()
