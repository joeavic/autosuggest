import json
import redis

# create a redis client
redisClient = redis.StrictRedis(host='localhost', port=6379,db=0)

input_file = open ('data.json')
json_array = json.load(input_file)

for item in json_array:
    # split each word of the city
    names = item['name'].split(" ")
    for name in names:
        # create all sets of name and put as key in redis 
        for l in range(1, len(name) + 1):
           key = "CITY_" + name[0:l].lower()
           # add name as key and data as value 
           redisClient.zadd(key, {json.dumps(item) : 0})

    state = item['state']
    for l in range(1, len(state) + 1):
        key = "STATE_" + state[0:l].lower()
        # add name as key and data as value
        redisClient.zadd(key, {json.dumps(item) : 0})















