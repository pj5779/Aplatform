<template>
    <form class="contact-form form-style-4 form-style-4-text-dark">
        <div id="examples" class="container py-2">
            <div class="col pb-3 pt-3">
                <div class="row">
                    <h1 class="form-group col-md-12">
                        <input type="text" value="" maxlength="20" class="form-control text-3 h-auto py-2"
                                    name="name" placeholder="제목" required>
                    </h1>
                </div>
                <div class="row">
                    <section class="call-to-action with-full-borders mb-2 mainPadding">
                        <div class="col-sm-2 col-lg-2">
                            <div class="col-sm-12 col-lg-12 container">
                                이미지 사진
                                <!-- 여기 -->
                                <div class="position-absolute right-0 bottom-0">
                                    <a href="#" class="btn btn-primary btn-circle">
                                        <i class="fa fa-minus"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="row col-sm-10 col-lg-10">
                            <div class="col-sm-4 col-lg-4">
                                <input type="text" value="" maxlength="100" class="form-control text-3 h-auto py-2"
                                    name="name" placeholder="이름" required>
                            </div>
                            <div class="col-sm-3 col-lg-2">
                                <div class="form-check form-check-inline">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="radio" name="radios"
                                            id="tabContent16Radio1" value="201" required> 남
                                    </label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="radio" name="radios"
                                            id="tabContent16Radio2" value="202" required> 여
                                    </label>
                                </div>
                            </div>
                            <div class="col-sm-2 col-lg-2"><input type="text" value="" maxlength="100"
                                    class="form-control text-3 h-auto py-2" name="name" placeholder="생년월일" required>
                            </div>
                            <div class="col-sm-2 col-lg-4"><input type="text" value="" maxlength="100"
                                    class="form-control text-3 h-auto py-2" name="name" placeholder="전화번호" required>
                            </div>
                            <hr class="mt-3 mb-1" />
                            <div class="col-sm-2 col-lg-8"><input type="text" value="" maxlength="100"
                                    class="form-control text-3 h-auto py-2" name="name" placeholder="주소" required></div>
                            <div class="col-sm-3 col-lg-4"><input type="text" value="" maxlength="100"
                                    class="form-control text-3 h-auto py-2" name="name" placeholder="이메일" required>
                            </div>
                            <hr class="mt-3 mb-1" />
                            <div class="col-sm-4 col-lg-3"><input type="text" value="" maxlength="100"
                                    class="form-control text-3 h-auto py-2" name="name" placeholder="희망연봉" required>
                            </div>
                            <div class="col-sm-3 col-lg-3"><input type="text" value="" maxlength="100"
                                    class="form-control text-3 h-auto py-2" name="name" placeholder="최종학력" required>
                            </div>
                            <div class="col-sm-3 col-lg-3"><input type="text" value="" maxlength="100"
                                    class="form-control text-3 h-auto py-2" name="name" placeholder="학점" required></div>
                            <div class="col-sm-3 col-lg-3"></div>
                        </div>
                    </section>
                    <div class="row">

                    </div>
                </div>
            </div>
            <div class="col pb-3 pt-1">
                <h4 class="mb-0">학력 <a href="#" class="btn btn-primary btn-circle">
                                    <i class="fa fa-plus"></i>
                                </a></h4>
                <hr class="mt-1 mb-2">
                <div>
                    여기에 컴포넌트
                </div>
            </div>
            <div class="col pb-3 pt-1">
                <h4 class="mb-0">경력 <a href="#" class="btn btn-primary btn-circle">
                                    <i class="fa fa-plus"></i>
                                </a></h4>
                <hr class="mt-1 mb-2">
                <div>
                    여기에 컴포넌트
                </div>
            </div>
            <div class="col pb-3 pt-1">
                <h4 class="mb-0">스킬 <a href="#" class="btn btn-primary btn-circle">
                                    <i class="fa fa-plus"></i>
                                </a>
                            </h4>
                <hr class="mt-1 mb-2">
                <div>
                    여기에 컴포넌트
                </div>
            </div>
            <div class="col pb-3 pt-1">
                <h4 class="mb-0">자격/면허증 <a href="#" class="btn btn-primary btn-circle">
                                    <i class="fa fa-plus"></i>
                                </a></h4>
                <hr class="mt-1 mb-2">
                <div>
                    여기에 컴포넌트
                </div>
            </div>
            <div class="col pb-3 pt-1">
                <h4 class="mb-0">자기소개서 <a href="#" class="btn btn-primary btn-circle">
                                    <i class="fa fa-plus"></i>
                                </a></h4>
                <hr class="mt-1 mb-2">
                <div>
                    여기에 컴포넌트
                </div>
            </div>
            <div class="col pb-3 pt-1">
                <div class="col-lg-4">
                    <h4 class="mb-0">첨부파일 <a href="#" class="btn btn-primary btn-circle">
                                    <i class="fa fa-plus"></i>
                                </a></h4>
                    <hr class="mt-1 mb-2">
                    <div>
                        여기에 컴포넌트
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="float-end">
                        <a href="#" class="btn btn-outline btn-xl btn-primary mb-2">저장</a>
                        <a href="#" class="btn btn-outline btn-xl btn-light mb-2">취소</a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</template>

<script setup></script>

<style scoped>
    .icon-featured {
        width: 30px;
        height: 30px;
        line-height: 30px;
        font-size: 20px;
    }

</style>
