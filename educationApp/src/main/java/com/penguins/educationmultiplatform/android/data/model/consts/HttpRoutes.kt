package com.penguins.educationmultiplatform.android.data.model.consts

object HttpRoutes {
    private const val BASE_URL = "http://ovz2.nikbakland.me78p.vps.myjino.ru:49338"
    //private const val BASE_URL = "http://10.2.2.2:49338"
    const val REGISTER = "$BASE_URL/auth/register"
    const val REGISTERVK = "$BASE_URL/auth/register/vk"
    const val LOGIN_EMAIL = "$BASE_URL/auth/login/email"
    const val GET_NEWS = "$BASE_URL/news"
}