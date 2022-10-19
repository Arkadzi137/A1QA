package Enum;

public enum StatusCodes {

    OK(200),
    EMPTY(404),
    POST(201);

    private int status;

    StatusCodes(int status) {
        this.status = status;
    }

    public int getStatus(){
        return status;
    }
}
