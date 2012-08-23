package com.qualcomm.myamba;

import android.app.Application;
import android.database.MatrixCursor;
import android.provider.BaseColumns;

public class YambaApp extends Application {
	static final String C_CREATED_AT = "createdAt";
	static final String C_USER = "user";
	static final String C_MESSAGE = "message";
	static final String[] COLUMN_NAMES = { BaseColumns._ID, C_CREATED_AT,
			C_USER, C_MESSAGE };
	MatrixCursor cursor;

}
