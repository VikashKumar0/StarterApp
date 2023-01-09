package com.vikash.login.data.models

data class LoginResponse(var user: User, var errors: LoginError)


data class User(
    var email: String,
    var username: String,
    var bio: String,
    var image: String,
    var token: String,
)
data class LoginError(
    var email: List<String>,
    var username: List<String>
)

//{
//    "user": {
//    "email": "{{EMAIL}}",
//    "username": "{{USERNAME}}",
//    "bio": null,
//    "image": "https://api.realworld.io/images/smiley-cyrus.jpeg",
//    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Int7RU1BSUx9fSIsInVzZXJuYW1lIjoie3tVU0VSTkFNRX19IiwiaWF0IjoxNjcxNDI5MzI3LCJleHAiOjE2NzY2MTMzMjd9.NyK5cbEXAhrm1aDEFTUqUywFY4pJQAllnhgObXM39nA"
//}
//}

//"errors": {
//    "email": [
//    "has already been taken"
//    ],
//    "username": [
//    "has already been taken"
//    ]
//}
