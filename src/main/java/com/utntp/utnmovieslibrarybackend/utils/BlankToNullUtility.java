package com.utntp.utnmovieslibrarybackend.utils;

import jakarta.annotation.Nullable;

public class BlankToNullUtility {

    public BlankToNullUtility() {
    }

    public String blankToNull(@Nullable String value){
        return (value == null || value.isBlank()) ? null : value;
    }
}
