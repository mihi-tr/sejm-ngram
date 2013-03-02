from django.db import models

class SearchedText(models.Model):
    text        = models.CharField(max_length=200)
    start_date  = models.DateTimeField('starting date')
    end_date    = models.DateTimeField('ending date')

    def __unicode__(self):
        return self.text + "(" + selft.start_date + ":" + selft.end_date + ")"
