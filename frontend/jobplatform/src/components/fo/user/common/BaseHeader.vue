<template>
  <header id="header" class="header-effect-shrink"
    data-plugin-options="{'stickyEnabled': true, 'stickyEffect': 'shrink', 'stickyEnableOnBoxed': true, 'stickyEnableOnMobile': false, 'stickyChangeLogo': true, 'stickyStartAt': 120, 'stickyHeaderContainerHeight': 70}">
    <div class="header-body border-top-0">
      <div class="header-top header-top-default border-bottom-0 bg-color-primary">
        <div class="container">
          <div class="header-row py-2">
            <div class="header-column justify-content-start">
              <div class="header-row">
                <nav class="header-nav-top">
                  <ul class="nav nav-pills">
                    <li class="nav-item">
                      <span class="text-light opacity-7 ps-0">Call Us Today! 800-123-4567</span>
                    </li>
                  </ul>
                </nav>
              </div>
            </div>
            <div class="header-column justify-content-end">
              <div class="header-row">
                <nav class="header-nav-top">
                  <ul class="nav nav-pills text-uppercase text-2">
                    <li class="nav-item nav-item-anim-icon d-none d-md-block">
                      <a class="nav-link ps-0 text-light opacity-7" href="about-us.html"><i
                          class="fas fa-angle-right"></i> About Us</a>
                    </li>
                    <li class="nav-item nav-item-anim-icon d-none d-md-block">
                      <a class="nav-link text-light opacity-7 pe-0" href="contact-us.html"><i
                          class="fas fa-angle-right"></i> Contact Us</a>
                    </li>
                  </ul>
                </nav>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="header-container header-container-md container">
        <div class="header-row">
          <div class="header-column">
            <div class="header-row">
              <div class="header-logo">
                <a href="/">
                  <img alt="Porto" width="100" height="48" data-sticky-width="82" data-sticky-height="40"
                    src="img/logo-default-slim.png" />
                </a>
              </div>
            </div>
          </div>
          <div class="header-column justify-content-end">
            <div class="header-row">
              <div class="header-nav header-nav-line header-nav-bottom-line">
                <div
                  class="header-nav-main header-nav-main-square header-nav-main-dropdown-no-borders header-nav-main-effect-2 header-nav-main-sub-effect-1">
                  <nav class="closed" style="">
                    <ul class="nav nav-pills" id="mainNav">
                      <li class="dropdown">
                        <router-link to="/applys/apply-list" a class="dropdown-item dropdown-toggle">
                          <h4>지원 리스트</h4>
                        </router-link>
                      </li>

                      <!-- <li class="dropdown">
                        <router-link to="/applys/applyDetail" a class="dropdown-item dropdown-toggle"><h4>지원 디테일</h4></router-link>
                      </li> -->

                      <li class="dropdown">
                        <router-link to="/resumes/resume-list" a class="dropdown-item dropdown-toggle">
                          <h4>이력서 리스트</h4>
                        </router-link>
                      </li>
                      <li class="dropdown">
                        <router-link to="/resumes/resume-insert" a class="dropdown-item dropdown-toggle">
                          <h4>이력서 작성</h4>
                        </router-link>
                      </li>
                      <li class="dropdown">
                        <router-link to="/resumes/resume-detail" a class="dropdown-item dropdown-toggle">
                          <h4>이력서 상세(컴포넌트 연습)</h4>
                        </router-link>
                      </li>
                    </ul>
                  </nav>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script setup></script>

<style></style>
