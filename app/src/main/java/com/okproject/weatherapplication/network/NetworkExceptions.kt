package com.okproject.weatherapplication.network

sealed class ApiException(message: String): Exception(message)

class ApiResponseException(message: String): ApiException(message)
class EmptyResponseBodyException(message: String): ApiException(message)