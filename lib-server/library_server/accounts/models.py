from django.db import models
from django.contrib.auth.models import User


class Profile(models.Model):
    """
    用户扩展信息：基于 Django 自带的 User 做一对一扩展
    """
    user = models.OneToOneField(
        User,
        on_delete=models.CASCADE,
        related_name='profile',
        verbose_name='用户'
    )
    student_id = models.CharField(
        '学号',
        max_length=20,
        unique=True
    )

    def __str__(self):
        return f'{self.student_id}'
    
from django.db.models.signals import post_save
from django.dispatch import receiver


@receiver(post_save, sender=User)
def create_or_update_user_profile(sender, instance, created, **kwargs):
    """
    创建或更新用户时，同时维护 Profile
    """
    if created:
        Profile.objects.create(user=instance)
    else:
        instance.profile.save()