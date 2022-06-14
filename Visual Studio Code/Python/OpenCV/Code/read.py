import cv2 as cv

# img = cv.imread('D:\Code\Visual Studio Code\Python\OpenCV\Img\con_meo.jpg')

# cv.imshow('Cat', img)

# cv.waitKey(0)

capture = cv.VideoCapture(0)

while True:
    isTrue, frame = capture.read()
    cv.imshow('Video', frame)

    if cv.waitKey(20) & 0xFF==ord('d'):
        break

capture.release()
cv.destroyAllWindows()