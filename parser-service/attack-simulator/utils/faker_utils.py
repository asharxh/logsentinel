from faker import Faker

fake = Faker()

def random_ip():
    return fake.ipv4()

def random_username():
    return fake.user_name()