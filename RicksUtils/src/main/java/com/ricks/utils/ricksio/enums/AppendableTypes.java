package com.ricks.utils.ricksio.enums;

public enum AppendableTypes {
    STRING_BUILDER, STRING_BUFFER;

    @Override
    public String toString() {
        switch (this) {
            case STRING_BUFFER: return StringBuffer.class.getName();
            case STRING_BUILDER: return StringBuilder.class.getName();
            default: return "UNSUPPORTED_APPENDABLE";
        }
    }
}