FROM python:latest
EXPOSE 6379

LABEL Maintainer = "akhilesh.joshi"

WORKDIR /

COPY auto_suggest.py ./
COPY data.json ./

#install deps
RUN pip3 install redis


CMD ["python", "./auto_suggest.py"]
