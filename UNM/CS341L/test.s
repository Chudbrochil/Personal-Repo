	.file	"test.c"
	.text
	.globl	_isEven
	.def	_isEven;	.scl	2;	.type	32;	.endef
_isEven:
LFB0:
	.cfi_startproc
	testb	$1, 4(%esp)
	sete	%al
	movzbl	%al, %eax
	ret
	.cfi_endproc
LFE0:
	.def	___main;	.scl	2;	.type	32;	.endef
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB1:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	andl	$-16, %esp
	call	___main
	movl	$0, %eax
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1:
	.ident	"GCC: (GNU) 5.3.0"
