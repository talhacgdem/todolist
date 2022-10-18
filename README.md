
# TODO LIST Project

This project works with your to-do list. The actions you can do are: adding, daily listing, weekly listing, confirming, rejecting, deleting.

## [POST] todo/new
### Add new todo item
### RequestBody
```javascript
{
    "date"          :"yyyy-MM-dd",
    "description"   :"message"
}
```
![new_todo.png](https://github.com/talhacgdem/todolist/images/new_todo.png) <br/>  <br/>


## [GET] todo/getDaily
### List todos for today
```javascript
[
    {
        "id": 1,
        "date": "19 Ekim 2022, Çarşamba",
        "description": "bugünün mesajı Emine için :)",
        "status": false
    }
]
```

## [GET] todo/getWeekly
### List todos for this week
```javascript
[
    {
        "id": 1,
        "date": "19 Ekim 2022, Çarşamba",
        "description": "bugünün mesajı Emine için :)",
        "status": false
    }
        ...
]
```
![weekly.png](https://github.com/talhacgdem/todolist/images/weekly.png) <br/>  <br/>

## [GET] todo/accept/{id}
### Accept todo item from given id
### If id is null!
![accept_without_id.png](https://github.com/talhacgdem/todolist/images/accept_without_id.png) <br/>  <br/> 

## [GET] todo/reject/{id}
### Reject todo item from given id
![reject.png](https://github.com/talhacgdem/todolist/images/reject.png) <br/>  <br/> 

## [GET] todo/delete/{id}
### Delete todo item from given id
