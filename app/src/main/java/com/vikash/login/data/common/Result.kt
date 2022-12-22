package com.vikash.login.data.common
/*
 * Name: Result.kt
 * Created by: Vikash on 20/12/2022.
 * Copyright © 2022 . All rights reserved.
 */

/**
 * A generic Union type to hold a Success OR Failure.
 */
sealed class Result<out A, out B> {
    data class Failure<A>(val value: A) : Result<A, Nothing>()
    data class Success<B>(val value: B) : Result<Nothing, B>()

    companion object {
        fun <A> failure(value: A): Result<A, Nothing> {
            return Failure(value)
        }

        fun <B> success(value: B): Result<Nothing, B> {
            return Success(value)
        }
    }
}

/**
 * Wraps [T] in a [Result.Failure]
 */
fun <T> T.toFailure(): Result<T, Nothing> {
    return Result.Failure(this)
}

/**
 * Wraps [T] in a [Result.Success]
 */
fun <T> T.toSuccess(): Result<Nothing, T> {
    return Result.Success(this)
}
