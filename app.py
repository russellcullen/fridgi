import tornado.ioloop
import tornado.web
import os

class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("Hello. Welcome to Fridgi. The fridge of the future. ")

application = tornado.web.Application([
    (r"/", MainHandler),
])

if __name__ == "__main__":
    port = int(os.environ.get('PORT', 5000))
    application.listen(port)
    tornado.ioloop.IOLoop.instance().start()
