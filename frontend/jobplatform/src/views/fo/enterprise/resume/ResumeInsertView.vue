<template>
  <form class="contact-form form-style-4 form-style-4-text-dark">
    <div id="examples" class="container py-2">
      <div class="col pb-3 pt-3">
        <div class="row">
          <h1 class="form-group col-md-12">
            <strong class="font-weight-extra-bold"> 이력서 등록 </strong>
          </h1>
          <hr class="gradient" />
        </div>
        <div class="col pb-3 pt-3">
          <div class="row">
            <div class="form-group col-md-6">
              <input type="text" value="" maxlength="20" class="form-control text-3 h-auto py-2" name="name" placeholder="제목" required />
            </div>
          </div>
          <div class="row">
            <section class="call-to-action with-full-borders mb-2 mainContainer">
              <div class="col-sm-2 col-lg-2">
                <div class="col-sm-12 col-lg-12 imgContainer">
                  <span class="img-thumbnail d-block">
                    <img
                      class="img-fluid"
                      src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdraZyZ8CC81iPsZST1_TLB0SBOXC6wfvQ_Exx1uKkGsFpi4t_Mj4o8HhM_vPwI60yBF8&usqp=CAU"
                      alt
                      style="width: 200px"
                    />
                    <a href="#" class="imgIcon">
                      <i class="fa-regular fa-trash-can"></i>
                    </a>
                  </span>
                </div>
              </div>
              <div class="row col-sm-10 col-lg-10">
                <div class="col-sm-4 col-lg-4">
                  <input type="text" value="" maxlength="100" class="form-control text-3 h-auto py-2" name="name" placeholder="이름" required />
                </div>
                <div class="col-sm-3 col-lg-2">
                  <div class="form-check form-check-inline">
                    <label class="form-check-label">
                      <input class="form-check-input" type="radio" name="radios" id="tabContent16Radio1" value="201" required /> 남
                    </label>
                  </div>
                  <div class="form-check form-check-inline">
                    <label class="form-check-label">
                      <input class="form-check-input" type="radio" name="radios" id="tabContent16Radio2" value="202" required /> 여
                    </label>
                  </div>
                </div>
                <div class="col-sm-2 col-lg-2">
                  <input type="text" value="" maxlength="100" class="form-control text-3 h-auto py-2" name="name" placeholder="생년월일" required />
                </div>
                <div class="col-sm-2 col-lg-4">
                  <input type="text" value="" maxlength="100" class="form-control text-3 h-auto py-2" name="name" placeholder="전화번호" required />
                </div>
                <hr class="mt-3 mb-1" />
                <div class="col-sm-2 col-lg-8">
                  <input type="text" value="" maxlength="100" class="form-control text-3 h-auto py-2" name="name" placeholder="주소" required />
                </div>
                <div class="col-sm-3 col-lg-4">
                  <input type="text" value="" maxlength="100" class="form-control text-3 h-auto py-2" name="name" placeholder="이메일" required />
                </div>
                <hr class="mt-3 mb-1" />
                <div class="col-sm-4 col-lg-3">
                  <input type="text" value="" maxlength="100" class="form-control text-3 h-auto py-2" name="name" placeholder="희망연봉" required />
                </div>
                <div class="col-sm-3 col-lg-3">
                  <input type="text" value="" maxlength="100" class="form-control text-3 h-auto py-2" name="name" placeholder="최종학력" required />
                </div>
                <div class="col-sm-3 col-lg-3">
                  <input type="text" value="" maxlength="100" class="form-control text-3 h-auto py-2" name="name" placeholder="학점" required />
                </div>
                <div class="col-sm-3 col-lg-3"></div>
              </div>
            </section>
            <div class="row"></div>
          </div>
        </div>
        <div class="col pb-3 pt-1">
          <h4 class="mb-0">
            학력 &nbsp;
            <a href="#" class="btn btn-primary btn-circle mb-2">
              <i class="fa fa-plus"></i>
            </a>
          </h4>
          <hr class="mt-1 mb-2" />
          <div>여기에 컴포넌트</div>
        </div>
        <div class="col pb-3 pt-1">
          <h4 class="mb-0">
            경력 &nbsp;
            <a href="#" class="btn btn-primary btn-circle mb-2">
              <i class="fa fa-plus"></i>
            </a>
          </h4>
          <hr class="mt-1 mb-2" />
          <div>여기에 컴포넌트</div>
        </div>
        <div class="col pb-3 pt-1">
          <h4 class="mb-0">
            스킬 &nbsp;
            <a href="#" class="btn btn-primary btn-circle mb-2">
              <i class="fa fa-plus"></i>
            </a>
          </h4>
          <hr class="mt-1 mb-2" />
          <div>여기에 컴포넌트</div>
        </div>
        <div class="col pb-3 pt-1">
          <h4 class="mb-0">
            자격/면허증 &nbsp;
            <a href="#" class="btn btn-primary btn-circle mb-2">
              <i class="fa fa-plus"></i>
            </a>
          </h4>
          <hr class="mt-1 mb-2" />
          <div>여기에 컴포넌트</div>
        </div>
        <SelfintoductionsResume />
        <!-- <div class="col pb-3 pt-1">
                    <h4 class="mb-0">
                        자기소개서 &nbsp;
                        <a href="#" class="btn btn-primary btn-circle mb-2">
                            <i class="fa fa-plus"></i>
                        </a>
                    </h4>
                    <hr class="mt-1 mb-2" />
                    <div>여기에 컴포넌트</div>
                </div>  -->
        <div class="col pb-3 pt-1">
          <AttachmentsResume />
          <!-- <div class="col-lg-4">
                        <h4 class="mb-0">
                            첨부파일 &nbsp;
                            <a href="#" class="btn btn-primary btn-circle mb-2">
                                <i class="fa fa-plus"></i>
                            </a>
                        </h4>
                        <hr class="mt-1 mb-2" />
                        <div>여기에 컴포넌트</div>
                    </div> -->
          <div class="col-lg-12">
            <div class="float-end">
              <a href="#" class="btn btn-outline btn-xl btn-primary mb-2">저장</a>
              <a href="#" class="btn btn-outline btn-xl btn-light mb-2">취소</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </form>
</template>

<script setup>
import AttachmentsResume from "@/components/fo/enterprise/resume/AttachmentsResume.vue";
import SelfintoductionsResume from "@/components/fo/enterprise/resume/SelfintoductionsResume.vue";
</script>

<style scoped>
.mainContainer {
  padding-left: 10px;
  padding-right: 10px;
}

.imgContainer {
  padding-right: 5px;
}

.imgIcon {
  position: absolute;
  right: 10px;
  bottom: 5px;
  font-size: larger;
}
</style>
