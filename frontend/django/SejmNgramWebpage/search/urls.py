from django.conf.urls.defaults import patterns, include, url

urlpatterns = patterns('search.views',
    url(r'^$', 'index'),
    url(r'^(?P<search_text>\w+)/$', 'results'),
    url(r'^(?P<search_text>\w+)/go/$', 'vote'),
)
