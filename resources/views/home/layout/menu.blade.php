<section id="menu">
    <div class="menu mx-auto">

    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            {{-- this is logo --}}
          <a id="logo" class="navbar-brand" href="#">Logo</a>

            {{-- this is button responsive --}}
          <button class="navbar-toggler custom-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

            {{-- this is btn login --}}
          <div class="collapse navbar-collapse " id="navbarNavAltMarkup">
            <div class="navbar-nav ms-auto">


              {{-- Check the condition according to the login flag --}}
              @if(false)

                <div class="customLogin">
                  <a id="nar-contact-us" class="nav-link" href="#">Contact us</a>
                  <a id="login" class="nav-link" href="#">Login</a>
                  <a id="register" class="nav-link" href="#">Register</a>
                </div>

              @else

                <ul class="custom-menu-ul navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link" href="#">Buy</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Sell</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Aution</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Contact us</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"><i style="font-size: large" class="bi bi-bell-fill"></i></a>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      <i style="font-size: large" class="bi bi-person-fill"></i>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                      <li><a class="dropdown-item" style="color: black" href="#">Account settings</a></li>
                      <li><a class="dropdown-item" style="color: black" href="#">My assets</a></li>
                      <li><a class="dropdown-item" style="color: black" href="#">My Bids</a></li>
                      <li><a class="dropdown-item" style="color: black" href="#">Watch List</a></li>
                      <li><a class="dropdown-item" style="color: black" href="#">Log out</a></li>
                      <li><a class="dropdown-item" style="color: black" href="#">Transaction history</a></li>
                    </ul>
                  </li>
                </ul>
               
              @endif

            </div>
          </div>

        </div>
      </nav>

    </div>
</section>