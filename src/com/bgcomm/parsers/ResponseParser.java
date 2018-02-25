package com.bgcomm.parsers;

import com.bgcomm.models.Response;

public interface ResponseParser {
    Response parse(String res) throws Exception;
}
