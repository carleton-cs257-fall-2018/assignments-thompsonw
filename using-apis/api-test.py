'''
    api-test.py
    Will Thompson, 9/30/2016
    Data from a Weather App
'''

import sys
import json
import urllib.request
import urllib.error

def weather_by_city_name(city_name):

    url = 'https://api.openweathermap.org/data/2.5/weather?q={0}&APPID=561d34cb14fabed4dadc3fbec1f5ada1'.format(city_name)
    data_from_api = urllib.request.urlopen(url).read()
    decoded_data = data_from_api.decode('utf-8')
    city_weather_data = json.loads(decoded_data)  
    
    dictionary_of_weather_data = {}
    dictionary_of_weather_data['min_temperature'] = city_weather_data['main']['temp_min'] 
    dictionary_of_weather_data['humidity'] = city_weather_data['main']['humidity']
    dictionary_of_weather_data['pressure'] = city_weather_data['main']['pressure']
    dictionary_of_weather_data['max_temperature'] = city_weather_data['main']['temp_max'] 
    dictionary_of_weather_data['wind_speed'] = city_weather_data['wind']['speed'] 
    
    if 'deg' in city_weather_data['wind'].keys():
         dictionary_of_weather_data['wind_direction'] = city_weather_data['wind']['deg']
    
    dictionary_of_weather_data['weather_description'] = city_weather_data['weather'][0]['description']
    
    return dictionary_of_weather_data   
    
def weather_by_zip_code(zip_code):

    url = 'https://api.openweathermap.org/data/2.5/weather?zip={0}&APPID=561d34cb14fabed4dadc3fbec1f5ada1'.format(zip_code)
    data_from_api = urllib.request.urlopen(url).read()
    decoded_data = data_from_api.decode('utf-8')
    zip_code_weather_data = json.loads(decoded_data)
    return zip_code_weather_data
    
def main():

    print("Type a City name for a Weather update regarding that City.")
    print("Type a Zip Code for a Weather Update regarding the region of that Zip Code.")
    user_input = input("Type a city name or a valid United States Zip Code: ")  
    
    try:
        weather_for_inputted_city = weather_by_city_name(user_input)
    
    except urllib.error.HTTPError:
        print("This is not a valid City Name, please input a valid city Name")
        exit()
        
    
    print("Weather for: " + user_input)
    print(" ")        
    
    weather_data_keys = list(weather_for_inputted_city.keys())
    weather_data_values = list(weather_for_inputted_city.values())

    for i in range(len(weather_data_keys)):
        print(weather_data_keys[i], ": ", weather_data_values[i])
    
if __name__ == '__main__':
    main()