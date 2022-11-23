document.getElementById("sidebar").innerHTML = '\
<a href="/" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">\
            <span class="fs-5 fw-semibold">AMIR</span>\
        </a>\
        <ul class="list-unstyled ps-0">\
            <li class="mb-1">\
                <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"\
                        data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true">\
                    Меню\
                </button>\
                <div class="collapse show" id="home-collapse">\
                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">\
                        <li><a href=".." class="link-dark d-inline-flex text-decoration-none rounded">Главная</a></li>\
                        <li><a href="./manual"\
                               class="link-dark d-inline-flex text-decoration-none rounded">Руководство<br>пользователя</a>\
                        </li>\
                        <li><a href="./about" class="link-dark d-inline-flex text-decoration-none rounded">О нас</a>\
                        </li>\
                    </ul>\
                </div>\
            </li>\
            <li class="mb-1">\
                <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"\
                        data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="true">\
                    Заказы\
                </button>\
                <div class="collapse show" id="orders-collapse">\
                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">\
                        <li><a href="/srm/orders/new_order"\
                               class="link-dark d-inline-flex text-decoration-none rounded">Новый запрос</a></li>\
                        <li><a href="/srm/orders/current_orders"\
                               class="link-dark d-inline-flex text-decoration-none rounded">В процессе</a></li>\
                        <li><a href="/srm/orders/completed_orders"\
                               class="link-dark d-inline-flex text-decoration-none rounded">Завершенные</a></li>\
                        <li><a href="/srm/orders/canceled_orders"\
                               class="link-dark d-inline-flex text-decoration-none rounded">Отменённые</a></li>\
                    </ul>\
                </div>\
            </li>\
            <li class="border-top my-3"></li>\
            <div class="dropdown">\
                <a href="#" class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle"\
                   data-bs-toggle="dropdown" aria-expanded="false">\
                    <img src="https://cdn-icons-png.flaticon.com/512/417/417777.png" alt="" width="32" height="32"\
                         class="rounded-circle me-2">\
                    <strong th:text="${username}">12345</strong>\
                </a>\
                <ul class="dropdown-menu text-small shadow">\
                    <li><a class="dropdown-item" href="#">Настройки</a></li>\
                    <li><a class="dropdown-item" href="#">Профиль</a></li>\
                    <li>\
                        <hr class="dropdown-divider">\
                        <form action="" th:action="@{/logout}" method="post">\
                            <button class="dropdown-item" type="submit">Выйти</button>\
                        </form>\
                    </li>\
                    </ul>\
            </div>\
        </ul>'