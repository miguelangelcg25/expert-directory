# Experts directory

Springboot based app that exposes the users api. It uses an in memory database and some sample data.
To run the app open a terminal and excute the following:

```
cd expert-directory
mvn springboot:run
```


Users API:

GET - /v1/users
```
[
    ...
    {
        "id": 26,
        "name": "Teddy",
        "url": "https://github.com/teddykoker",
        "shortURL": "https://bit.ly/3da6Mie",
        "tags": [
            {
                "id": 1,
                "name": "Java"
            },
        ],
        "friends": 0
    }
    ...
]
```

GET - /v1/users/{id}
```
{
    "id": 26,
    "name": "Teddy",
    "url": "https://github.com/teddykoker",
    "shortURL": "https://bit.ly/3da6Mie",
    "tags": [
        {
            "id": 1,
            "name": "Java"
        },
    ],
    "friends": 0
}
```

POST - /v1/users
```
{
    "name": "Miguelangel",
    "url": "https://stackoverflow.com/users/844216/alex-ackerman"
}
```