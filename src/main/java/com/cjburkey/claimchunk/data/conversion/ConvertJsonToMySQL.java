package com.cjburkey.claimchunk.data.conversion;

import com.cjburkey.claimchunk.data.newdata.JsonDataHandler;
import com.cjburkey.claimchunk.data.newdata.MySQLDataHandler;

public class ConvertJsonToMySQL implements IDataConverter<JsonDataHandler, MySQLDataHandler> {

    @Override
    public MySQLDataHandler convert(JsonDataHandler oldDataHandler) throws Exception {
        // Create and a new MySQL data handler
        MySQLDataHandler newDataHandler = new MySQLDataHandler();

        // Convert from the old data handler to the new one
        IDataConverter.copyConvert(oldDataHandler, newDataHandler);

        // Return the new data handler
        return newDataHandler;
    }

}
