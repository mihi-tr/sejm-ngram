from django.shortcuts import render_to_response, get_object_or_404
from search.models import SearchedText
from django.http import HttpResponse, HttpResponseRedirect
from django.core.urlresolvers import reverse
from django.template import RequestContext

def index(request):
    latest_search_list = SearchedText.objects.all().order_by('text')[:5]
    return render_to_response('search/index.html', {'latest_search_list': latest_search_list})

def results(request, search_text):
    return HttpResponse("You're trying to search for text: %s." % search_text)

def vote(request, search_text):
    p = get_object_or_404(SearchedText, pk=search_text)
    try:
        selected_choice = p.choice_set.get(pk=request.POST['choice'])
    except (KeyError, Choice.DoesNotExist):
    # Redisplay the poll voting form.
        return render_to_response('polls/detail.html', {
                                  'poll': p,
                                  'error_message': "You didn't select a choice.",
                                  }, context_instance=RequestContext(request))
    else:
        selected_choice.votes += 1
        selected_choice.save()
        # Always return an HttpResponseRedirect after successfully dealing
        # with POST data. This prevents data from being posted twice if a
        # user hits the Back button.
        return HttpResponseRedirect(reverse('polls.views.results', args=(p.id,)))
