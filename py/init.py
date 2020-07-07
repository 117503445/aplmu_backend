import requests
import config
import json
import file_util


def init(mode: str):
    if mode == 'product':
        config.host = 'https://aplmu.backend.117503445.top'
    elif mode == 'local':
        config.host = 'http://localhost'
    else:
        print("mode not found")

    config.url_api = config.host+'/api'
    config.url_login = config.url_api+'/login'
    config.url_register = config.url_api+'/register'
    config.user = config.url_api+'/user'
    config.user_me = config.url_api+'/user/me'
    config.articleEntity = config.url_api+'/articleEntity'


def login(username, password):

    headers = {
        'accept': '*/*',
        'Content-Type': 'application/json',
    }

    data = '{ "password": "pd", "rememberMe": true, "username": "un"}'.replace(
        'un', username)  .  replace('pd', password)

    response = requests.post(
        config.url_login, headers=headers, data=data)
    print(response.text)
    jwt = 'Bearer ' + json.loads(response.text)['id_token']
    return jwt


def register():
    headers = {
        'accept': '*/*',
        'Content-Type': 'application/json',
    }

    data = '{ "avatar": "https://tse1-mm.cn.bing.net/th/id/OIP.hClfljxq29JkJrgiwR_v5gAAAA?pid=Api&rs=1", "email": "t117503445@gmail.com", "lastname": "last", "password": "hello", "username": "1175"}'

    res = requests.post(config.url_register, headers=headers, data=data)
    print(res.text)


def user_me(jwt):

    headers = {
        'accept': '*/*',
        'Authorization': jwt,
    }

    response = requests.get(config.user_me, headers=headers)
    print(response.text)
    return response.text


def get_id_from_user_me(res_text):
    js = json.loads(res_text)
    id = js['id']
    return id


def post_article(jwt, title, titleImage, content):

    headers = {
        'accept': '*/*',
        'Content-Type': 'application/json; charset=utf8',
        'Authorization': jwt
    }

    data = '{ "content": "ct", "title": "tt", "titleImage": "tm"}'.replace(
        'ct', content).replace('tt', title).replace('tm', titleImage).encode('utf-8')

    response = requests.post(
        config.articleEntity, headers=headers, data=data)
    print(response.text)


def main():
    init('local')
    admin_jwt = login("admin", "admin")

    print(admin_jwt)

    register()
    user_jwt = login("1175", "hello")
    user_id = get_id_from_user_me(user_me(user_jwt))
    print(user_id)
    post_article(user_jwt, 'Spring Boot 的 Docker 化实践', "https://tse1-mm.cn.bing.net/th/id/OIP.hClfljxq29JkJrgiwR_v5gAAAA?pid=Api&rs=1",
                 file_util.read_all_text('2020-06-18-Spring Boot 的 Docker 化实践.md'))


if __name__ == "__main__":
    main()
