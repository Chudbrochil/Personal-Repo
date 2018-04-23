	.file	"dictionary.c"
	.text
.Ltext0:
	.globl	h_mod
	.type	h_mod, @function
h_mod:
.LFB35:
	.file 1 "dictionary.c"
	.loc 1 119 0
	.cfi_startproc
.LVL0:
	.loc 1 122 0
	leaq	1(%rdi), %rsi
.LVL1:
	movzbl	(%rdi), %ecx
.LVL2:
	testb	%cl, %cl
	je	.L4
	.loc 1 123 0
	movl	tsize(%rip), %edi
	movl	$0, %edx
.LVL3:
.L3:
	movl	%edx, %eax
	sall	$7, %eax
	movsbl	%cl, %ecx
.LVL4:
	addl	%ecx, %eax
	movl	$0, %edx
.LVL5:
	divl	%edi
.LVL6:
	.loc 1 122 0
	addq	$1, %rsi
.LVL7:
	movzbl	-1(%rsi), %ecx
.LVL8:
	testb	%cl, %cl
	jne	.L3
	jmp	.L2
.LVL9:
.L4:
	.loc 1 120 0
	movl	$0, %edx
.LVL10:
.L2:
	.loc 1 125 0
	movl	%edx, %eax
	ret
	.cfi_endproc
.LFE35:
	.size	h_mod, .-h_mod
	.globl	h_add
	.type	h_add, @function
h_add:
.LFB36:
	.loc 1 129 0
	.cfi_startproc
.LVL11:
	.loc 1 132 0
	leaq	1(%rdi), %rsi
.LVL12:
	movzbl	(%rdi), %ecx
.LVL13:
	testb	%cl, %cl
	je	.L9
	movl	$0, %eax
.LVL14:
.L8:
	.loc 1 133 0
	movsbl	%cl, %ecx
.LVL15:
	addl	%ecx, %eax
.LVL16:
	.loc 1 132 0
	addq	$1, %rsi
.LVL17:
	movzbl	-1(%rsi), %ecx
.LVL18:
	testb	%cl, %cl
	jne	.L8
	jmp	.L7
.LVL19:
.L9:
	.loc 1 130 0
	movl	$0, %eax
.LVL20:
.L7:
	.loc 1 134 0
	movl	$0, %edx
	divl	tsize(%rip)
	.loc 1 135 0
	movl	%edx, %eax
	ret
	.cfi_endproc
.LFE36:
	.size	h_add, .-h_add
	.globl	h_xor
	.type	h_xor, @function
h_xor:
.LFB37:
	.loc 1 139 0
	.cfi_startproc
.LVL21:
	.loc 1 142 0
	leaq	1(%rdi), %rdx
.LVL22:
	movzbl	(%rdi), %ecx
.LVL23:
	testb	%cl, %cl
	je	.L14
	movl	$0, %eax
.LVL24:
.L13:
	.loc 1 143 0
	movsbl	%cl, %ecx
.LVL25:
	xorl	%eax, %ecx
.LVL26:
	sall	$4, %ecx
	shrl	$28, %eax
.LVL27:
	orl	%ecx, %eax
.LVL28:
	.loc 1 142 0
	addq	$1, %rdx
.LVL29:
	movzbl	-1(%rdx), %ecx
.LVL30:
	testb	%cl, %cl
	jne	.L13
	jmp	.L12
.LVL31:
.L14:
	.loc 1 140 0
	movl	$0, %eax
.LVL32:
.L12:
	.loc 1 144 0
	movl	$0, %edx
.LVL33:
	divl	tsize(%rip)
	.loc 1 145 0
	movl	%edx, %eax
	ret
	.cfi_endproc
.LFE37:
	.size	h_xor, .-h_xor
	.globl	compare_ele
	.type	compare_ele, @function
compare_ele:
.LFB42:
	.loc 1 240 0
	.cfi_startproc
.LVL34:
	.loc 1 243 0
	movq	(%rsi), %rax
	movq	(%rdi), %rdx
	movl	8(%rax), %eax
	subl	8(%rdx), %eax
	.loc 1 244 0
	ret
	.cfi_endproc
.LFE42:
	.size	compare_ele, .-compare_ele
	.section	.rodata.str1.8,"aMS",@progbits,1
	.align 8
.LC0:
	.string	"Couldn't allocate hash array, exiting\n"
	.text
	.type	new_table, @function
new_table:
.LFB33:
	.loc 1 83 0
	.cfi_startproc
.LVL35:
	subq	$8, %rsp
	.cfi_def_cfa_offset 16
	.loc 1 84 0
	movl	%edi, tsize(%rip)
	.loc 1 85 0
	movslq	%edi, %rdi
.LVL36:
	movl	$8, %esi
	call	calloc
.LVL37:
	movq	%rax, htable(%rip)
	.loc 1 86 0
	testq	%rax, %rax
	jne	.L17
.LVL38:
.LBB41:
.LBB42:
	.file 2 "/usr/include/x86_64-linux-gnu/bits/stdio2.h"
	.loc 2 97 0
	movq	stderr(%rip), %rcx
	movl	$38, %edx
	movl	$1, %esi
	movl	$.LC0, %edi
	call	fwrite
.LVL39:
.LBE42:
.LBE41:
	.loc 1 88 0
	movl	$1, %edi
	call	exit
.LVL40:
.L17:
	.loc 1 90 0
	addq	$8, %rsp
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE33:
	.size	new_table, .-new_table
	.globl	Strlen
	.type	Strlen, @function
Strlen:
.LFB32:
	.loc 1 57 0
	.cfi_startproc
.LVL41:
	.loc 1 59 0
	movl	$0, %eax
	movq	$-1, %rcx
	repnz scasb
.LVL42:
	movq	%rcx, %rax
	notq	%rax
	subq	$1, %rax
	.loc 1 68 0
	ret
	.cfi_endproc
.LFE32:
	.size	Strlen, .-Strlen
	.globl	lower1
	.type	lower1, @function
lower1:
.LFB30:
	.loc 1 30 0
	.cfi_startproc
.LVL43:
	pushq	%r12
	.cfi_def_cfa_offset 16
	.cfi_offset 12, -16
	pushq	%rbp
	.cfi_def_cfa_offset 24
	.cfi_offset 6, -24
	pushq	%rbx
	.cfi_def_cfa_offset 32
	.cfi_offset 3, -32
	movq	%rdi, %r12
.LVL44:
	.loc 1 33 0
	movl	$0, %ebp
	jmp	.L22
.LVL45:
.L24:
	.loc 1 34 0
	addq	%r12, %rbx
	movzbl	(%rbx), %eax
	leal	-65(%rax), %edx
	cmpb	$25, %dl
	ja	.L23
	.loc 1 35 0
	addl	$32, %eax
	movb	%al, (%rbx)
.L23:
	.loc 1 33 0 discriminator 2
	addl	$1, %ebp
.LVL46:
.L22:
	.loc 1 33 0 is_stmt 0 discriminator 1
	movslq	%ebp, %rbx
	movq	%r12, %rdi
	call	Strlen
.LVL47:
	cmpq	%rax, %rbx
	jb	.L24
	.loc 1 36 0 is_stmt 1
	popq	%rbx
	.cfi_def_cfa_offset 24
	popq	%rbp
	.cfi_def_cfa_offset 16
.LVL48:
	popq	%r12
	.cfi_def_cfa_offset 8
.LVL49:
	ret
	.cfi_endproc
.LFE30:
	.size	lower1, .-lower1
	.globl	lower2
	.type	lower2, @function
lower2:
.LFB31:
	.loc 1 40 0
	.cfi_startproc
.LVL50:
	pushq	%rbx
	.cfi_def_cfa_offset 16
	.cfi_offset 3, -16
	movq	%rdi, %rbx
	.loc 1 42 0
	call	Strlen
.LVL51:
	.loc 1 44 0
	testl	%eax, %eax
	jle	.L26
	movq	%rbx, %rdx
	leal	-1(%rax), %eax
.LVL52:
	leaq	1(%rbx,%rax), %rsi
.LVL53:
.L29:
	.loc 1 45 0
	movzbl	(%rdx), %eax
	leal	-65(%rax), %ecx
	cmpb	$25, %cl
	ja	.L28
	.loc 1 46 0
	addl	$32, %eax
	movb	%al, (%rdx)
.L28:
.LVL54:
	addq	$1, %rdx
.LVL55:
	.loc 1 44 0 discriminator 2
	cmpq	%rsi, %rdx
	jne	.L29
.L26:
	.loc 1 47 0
	popq	%rbx
	.cfi_def_cfa_offset 8
.LVL56:
	ret
	.cfi_endproc
.LFE31:
	.size	lower2, .-lower2
	.section	.rodata.str1.8
	.align 8
.LC1:
	.string	"Couldn't allocate hash element, exiting\n"
	.text
	.type	new_ele, @function
new_ele:
.LFB34:
	.loc 1 93 0
	.cfi_startproc
.LVL57:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	pushq	%rbx
	.cfi_def_cfa_offset 24
	.cfi_offset 3, -24
	subq	$8, %rsp
	.cfi_def_cfa_offset 32
	movq	%rdi, %rbp
	.loc 1 94 0
	movl	$24, %edi
.LVL58:
	call	malloc
.LVL59:
	movq	%rax, %rbx
.LVL60:
	.loc 1 95 0
	movq	%rbp, %rdi
	call	Strlen
.LVL61:
	.loc 1 96 0
	movl	llen(%rip), %edx
	cmpl	%edx, %eax
	jle	.L33
	.loc 1 97 0
	movq	%rbp, lstring(%rip)
	.loc 1 98 0
	movl	%eax, llen(%rip)
	.loc 1 99 0
	movl	$1, lcnt(%rip)
	jmp	.L34
.L33:
	.loc 1 100 0
	cmpl	%edx, %eax
	jne	.L34
	.loc 1 101 0
	addl	$1, lcnt(%rip)
.L34:
	.loc 1 102 0
	testq	%rbx, %rbx
	jne	.L35
.LVL62:
.LBB43:
.LBB44:
	.loc 2 97 0
	movq	stderr(%rip), %rcx
	movl	$40, %edx
	movl	$1, %esi
	movl	$.LC1, %edi
	call	fwrite
.LVL63:
.LBE44:
.LBE43:
	.loc 1 104 0
	movl	$1, %edi
	call	exit
.LVL64:
.L35:
	.loc 1 107 0
	movq	%rbp, (%rbx)
	.loc 1 108 0
	movl	$1, 8(%rbx)
	.loc 1 110 0
	movq	%rbx, %rax
.LVL65:
	addq	$8, %rsp
	.cfi_def_cfa_offset 24
	popq	%rbx
	.cfi_def_cfa_offset 16
.LVL66:
	popq	%rbp
	.cfi_def_cfa_offset 8
.LVL67:
	ret
	.cfi_endproc
.LFE34:
	.size	new_ele, .-new_ele
	.section	.rodata.str1.8
	.align 8
.LC2:
	.string	"Couldn't allocate space for string, exiting\n"
	.text
	.globl	save_string
	.type	save_string, @function
save_string:
.LFB38:
	.loc 1 152 0
	.cfi_startproc
.LVL68:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	pushq	%rbx
	.cfi_def_cfa_offset 24
	.cfi_offset 3, -24
	subq	$8, %rsp
	.cfi_def_cfa_offset 32
	movq	%rdi, %rbp
	.loc 1 153 0
	call	Strlen
.LVL69:
	leaq	1(%rax), %rdi
	call	malloc
.LVL70:
	.loc 1 154 0
	testq	%rax, %rax
	jne	.L38
.LVL71:
.LBB45:
.LBB46:
	.loc 2 97 0
	movq	stderr(%rip), %rcx
	movl	$44, %edx
	movl	$1, %esi
	movl	$.LC2, %edi
	call	fwrite
.LVL72:
.LBE46:
.LBE45:
	.loc 1 156 0
	movl	$1, %edi
	call	exit
.LVL73:
.L38:
	movq	%rax, %rbx
.LVL74:
.LBB47:
.LBB48:
	.file 3 "/usr/include/x86_64-linux-gnu/bits/string3.h"
	.loc 3 110 0
	movq	%rbp, %rsi
	movq	%rax, %rdi
	call	strcpy
.LVL75:
.LBE48:
.LBE47:
	.loc 1 160 0
	movq	%rbx, %rax
	addq	$8, %rsp
	.cfi_def_cfa_offset 24
	popq	%rbx
	.cfi_def_cfa_offset 16
.LVL76:
	popq	%rbp
	.cfi_def_cfa_offset 8
.LVL77:
	ret
	.cfi_endproc
.LFE38:
	.size	save_string, .-save_string
	.globl	find_ele_rec
	.type	find_ele_rec, @function
find_ele_rec:
.LFB39:
	.loc 1 164 0
	.cfi_startproc
.LVL78:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	pushq	%rbx
	.cfi_def_cfa_offset 24
	.cfi_offset 3, -24
	subq	$8, %rsp
	.cfi_def_cfa_offset 32
	movq	%rsi, %rbp
	.loc 1 165 0
	testq	%rdi, %rdi
	jne	.L41
	.loc 1 167 0
	addl	$1, ucnt(%rip)
	.loc 1 168 0
	movq	%rsi, %rdi
.LVL79:
	call	save_string
.LVL80:
	movq	%rax, %rdi
	call	new_ele
.LVL81:
	jmp	.L42
.LVL82:
.L41:
	movq	%rdi, %rbx
	.loc 1 170 0
	movq	(%rdi), %rsi
.LVL83:
	movq	%rbp, %rdi
.LVL84:
	call	strcmp
.LVL85:
	testl	%eax, %eax
	jne	.L43
	.loc 1 171 0
	movl	8(%rbx), %eax
	leal	1(%rax), %edx
	movl	%edx, 8(%rbx)
	.loc 1 176 0
	movq	%rbx, %rax
	.loc 1 172 0
	cmpl	mcnt(%rip), %edx
	jle	.L42
.LVL86:
	.loc 1 173 0
	movl	%edx, mcnt(%rip)
	.loc 1 174 0
	movq	(%rbx), %rax
.LVL87:
	movq	%rax, mstring(%rip)
	.loc 1 176 0
	movq	%rbx, %rax
	jmp	.L42
.L43:
	.loc 1 178 0
	movq	16(%rbx), %rdi
	movq	%rbp, %rsi
	call	find_ele_rec
.LVL88:
	movq	%rax, 16(%rbx)
	.loc 1 179 0
	movq	%rbx, %rax
.LVL89:
.L42:
	.loc 1 180 0
	addq	$8, %rsp
	.cfi_def_cfa_offset 24
	popq	%rbx
	.cfi_def_cfa_offset 16
	popq	%rbp
	.cfi_def_cfa_offset 8
.LVL90:
	ret
	.cfi_endproc
.LFE39:
	.size	find_ele_rec, .-find_ele_rec
	.globl	find_ele_iter_f
	.type	find_ele_iter_f, @function
find_ele_iter_f:
.LFB40:
	.loc 1 184 0
	.cfi_startproc
.LVL91:
	pushq	%r13
	.cfi_def_cfa_offset 16
	.cfi_offset 13, -16
	pushq	%r12
	.cfi_def_cfa_offset 24
	.cfi_offset 12, -24
	pushq	%rbp
	.cfi_def_cfa_offset 32
	.cfi_offset 6, -32
	pushq	%rbx
	.cfi_def_cfa_offset 40
	.cfi_offset 3, -40
	subq	$8, %rsp
	.cfi_def_cfa_offset 48
	movq	%rdi, %r13
	movq	%rsi, %r12
.LVL92:
	.loc 1 186 0
	testq	%rdi, %rdi
	je	.L47
	movq	%rdi, %rbx
.LVL93:
.L50:
.LBB49:
	.loc 1 187 0
	movq	(%rbx), %rbp
.LVL94:
	.loc 1 188 0
	movq	%rbp, %rsi
	movq	%r12, %rdi
	call	strcmp
.LVL95:
	testl	%eax, %eax
	jne	.L48
.LBB50:
	.loc 1 189 0
	movl	8(%rbx), %eax
	leal	1(%rax), %edx
	movl	%edx, 8(%rbx)
.LVL96:
	.loc 1 194 0
	movq	%r13, %rax
	.loc 1 190 0
	cmpl	mcnt(%rip), %edx
	jle	.L49
.LVL97:
	.loc 1 191 0
	movl	%edx, mcnt(%rip)
	.loc 1 192 0
	movq	%rbp, mstring(%rip)
	jmp	.L49
.LVL98:
.L48:
.LBE50:
.LBE49:
	.loc 1 186 0 discriminator 2
	movq	16(%rbx), %rbx
.LVL99:
	testq	%rbx, %rbx
	jne	.L50
.LVL100:
.L47:
	.loc 1 197 0
	movq	%r12, %rdi
	call	save_string
.LVL101:
	movq	%rax, %rdi
	call	new_ele
.LVL102:
	.loc 1 198 0
	addl	$1, ucnt(%rip)
	.loc 1 199 0
	movq	%r13, 16(%rax)
.LVL103:
.L49:
	.loc 1 201 0
	addq	$8, %rsp
	.cfi_def_cfa_offset 40
	popq	%rbx
	.cfi_def_cfa_offset 32
	popq	%rbp
	.cfi_def_cfa_offset 24
	popq	%r12
	.cfi_def_cfa_offset 16
.LVL104:
	popq	%r13
	.cfi_def_cfa_offset 8
.LVL105:
	ret
	.cfi_endproc
.LFE40:
	.size	find_ele_iter_f, .-find_ele_iter_f
	.globl	find_ele_iter_r
	.type	find_ele_iter_r, @function
find_ele_iter_r:
.LFB41:
	.loc 1 205 0
	.cfi_startproc
.LVL106:
	pushq	%r13
	.cfi_def_cfa_offset 16
	.cfi_offset 13, -16
	pushq	%r12
	.cfi_def_cfa_offset 24
	.cfi_offset 12, -24
	pushq	%rbp
	.cfi_def_cfa_offset 32
	.cfi_offset 6, -32
	pushq	%rbx
	.cfi_def_cfa_offset 40
	.cfi_offset 3, -40
	subq	$8, %rsp
	.cfi_def_cfa_offset 48
	movq	%rsi, %r12
.LVL107:
	.loc 1 208 0
	testq	%rdi, %rdi
	je	.L55
	movq	%rdi, %r13
	movq	%rdi, %rbx
	jmp	.L58
.LVL108:
.L60:
	movq	%rax, %rbx
.LVL109:
.L58:
.LBB51:
	.loc 1 209 0
	movq	(%rbx), %rbp
.LVL110:
	.loc 1 210 0
	movq	%rbp, %rsi
	movq	%r12, %rdi
	call	strcmp
.LVL111:
	testl	%eax, %eax
	jne	.L56
.LBB52:
	.loc 1 211 0
	movl	8(%rbx), %eax
	leal	1(%rax), %edx
	movl	%edx, 8(%rbx)
.LVL112:
	.loc 1 216 0
	movq	%r13, %rax
	.loc 1 212 0
	cmpl	mcnt(%rip), %edx
	jle	.L62
.LVL113:
	.loc 1 213 0
	movl	%edx, mcnt(%rip)
	.loc 1 214 0
	movq	%rbp, mstring(%rip)
	jmp	.L62
.LVL114:
.L56:
.LBE52:
.LBE51:
	.loc 1 208 0 discriminator 2
	movq	16(%rbx), %rax
.LVL115:
	testq	%rax, %rax
	jne	.L60
	.loc 1 220 0
	movq	%r12, %rdi
	call	save_string
.LVL116:
	movq	%rax, %rdi
	call	new_ele
.LVL117:
	.loc 1 221 0
	addl	$1, ucnt(%rip)
	.loc 1 222 0
	movq	$0, 16(%rax)
	.loc 1 223 0
	testq	%rbx, %rbx
	je	.L62
	.loc 1 224 0
	movq	%rax, 16(%rbx)
	.loc 1 225 0
	movq	%r13, %rax
.LVL118:
	jmp	.L62
.LVL119:
.L55:
	.loc 1 220 0
	movq	%rsi, %rdi
.LVL120:
	call	save_string
.LVL121:
	movq	%rax, %rdi
	call	new_ele
.LVL122:
	.loc 1 221 0
	addl	$1, ucnt(%rip)
	.loc 1 222 0
	movq	$0, 16(%rax)
.LVL123:
.L62:
	.loc 1 228 0
	addq	$8, %rsp
	.cfi_def_cfa_offset 40
	popq	%rbx
	.cfi_def_cfa_offset 32
	popq	%rbp
	.cfi_def_cfa_offset 24
	popq	%r12
	.cfi_def_cfa_offset 16
.LVL124:
	popq	%r13
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE41:
	.size	find_ele_iter_r, .-find_ele_iter_r
	.globl	sort_words
	.type	sort_words, @function
sort_words:
.LFB43:
	.loc 1 249 0
	.cfi_startproc
.LVL125:
	pushq	%r13
	.cfi_def_cfa_offset 16
	.cfi_offset 13, -16
	pushq	%r12
	.cfi_def_cfa_offset 24
	.cfi_offset 12, -24
	pushq	%rbp
	.cfi_def_cfa_offset 32
	.cfi_offset 6, -32
	pushq	%rbx
	.cfi_def_cfa_offset 40
	.cfi_offset 3, -40
	subq	$8, %rsp
	.cfi_def_cfa_offset 48
	movl	%edi, %r12d
.LVL126:
	.loc 1 252 0
	movslq	ucnt(%rip), %rdi
.LVL127:
	movl	$8, %esi
	call	calloc
.LVL128:
	movq	%rax, %rbx
.LVL129:
	.loc 1 255 0
	movl	$0, scnt(%rip)
.LVL130:
	.loc 1 256 0
	movl	tsize(%rip), %r10d
	testl	%r10d, %r10d
	jle	.L65
	.loc 1 257 0
	movq	htable(%rip), %r11
	movl	$0, %r9d
	movl	$0, %ebp
.LVL131:
.L74:
	movq	(%r11,%r9,8), %rsi
.LVL132:
	testq	%rsi, %rsi
	je	.L66
	movl	scnt(%rip), %edi
	movl	$0, %r8d
.L72:
	.loc 1 258 0
	movl	8(%rsi), %r13d
	cmpl	$1, %r13d
	jne	.L67
	.loc 1 259 0
	addl	$1, %edi
	movl	$1, %r8d
.L67:
	.loc 1 260 0
	testl	%r12d, %r12d
	jne	.L68
.LVL133:
	.loc 1 263 0 discriminator 1
	testl	%ebp, %ebp
	jle	.L80
	.loc 1 263 0 is_stmt 0
	movslq	%ebp, %rax
	salq	$3, %rax
	movq	-8(%rbx,%rax), %rcx
	movl	%ebp, %edx
	cmpl	8(%rcx), %r13d
	jg	.L83
.LVL134:
	jmp	.L69
.LVL135:
.L68:
	.loc 1 261 0 is_stmt 1
	movslq	%ebp, %rax
	movq	%rsi, (%rbx,%rax,8)
	jmp	.L71
.LVL136:
.L83:
	.loc 1 264 0 discriminator 4
	movq	%rcx, (%rbx,%rax)
.LVL137:
	.loc 1 263 0 discriminator 4
	subl	$1, %edx
.LVL138:
	je	.L69
	.loc 1 263 0 is_stmt 0 discriminator 3
	movslq	%edx, %rax
	salq	$3, %rax
	movq	-8(%rbx,%rax), %rcx
	movl	8(%rcx), %r13d
	cmpl	%r13d, 8(%rsi)
	jg	.L83
	jmp	.L69
.LVL139:
.L80:
	.loc 1 263 0
	movl	%ebp, %edx
.LVL140:
.L69:
	.loc 1 265 0 is_stmt 1
	movslq	%edx, %rdx
	movq	%rsi, (%rbx,%rdx,8)
.LVL141:
.L71:
	.loc 1 267 0 discriminator 2
	addl	$1, %ebp
.LVL142:
	.loc 1 257 0 discriminator 2
	movq	16(%rsi), %rsi
.LVL143:
	testq	%rsi, %rsi
	jne	.L72
	testb	%r8b, %r8b
	je	.L66
	movl	%edi, scnt(%rip)
.L66:
.LVL144:
	addq	$1, %r9
.LVL145:
	.loc 1 256 0 discriminator 2
	cmpl	%r9d, %r10d
	jg	.L74
	.loc 1 269 0
	testl	%r12d, %r12d
	je	.L75
	jmp	.L78
.LVL146:
.L82:
	.loc 1 254 0
	movl	$0, %ebp
.LVL147:
.L78:
	.loc 1 270 0
	movslq	%ebp, %rsi
	movl	$compare_ele, %ecx
	movl	$8, %edx
	movq	%rbx, %rdi
	call	qsort
.LVL148:
.L75:
	.loc 1 272 0
	movq	(%rbx), %r12
.LVL149:
	.loc 1 273 0
	leal	-1(%rbp), %eax
	testl	%eax, %eax
	jle	.L76
	movq	%rbx, %rax
	leal	-2(%rbp), %edx
	leaq	8(%rbx,%rdx,8), %rsi
.LVL150:
.L77:
	.loc 1 274 0 discriminator 3
	movq	(%rax), %rdx
	movq	8(%rax), %rcx
	movq	%rcx, 16(%rdx)
	addq	$8, %rax
	.loc 1 273 0 discriminator 3
	cmpq	%rsi, %rax
	jne	.L77
	jmp	.L76
.LVL151:
.L89:
	.loc 1 272 0
	movq	(%rbx), %r12
.LVL152:
	.loc 1 254 0
	movl	$0, %ebp
	jmp	.L76
.LVL153:
.L65:
	.loc 1 269 0
	testl	%r12d, %r12d
	je	.L89
	jmp	.L82
.LVL154:
.L76:
	.loc 1 275 0
	movslq	%ebp, %rbp
	movq	-8(%rbx,%rbp,8), %rax
	movq	$0, 16(%rax)
	.loc 1 276 0
	movq	%rbx, %rdi
	call	free
.LVL155:
	.loc 1 278 0
	movq	%r12, %rax
	addq	$8, %rsp
	.cfi_def_cfa_offset 40
	popq	%rbx
	.cfi_def_cfa_offset 32
.LVL156:
	popq	%rbp
	.cfi_def_cfa_offset 24
	popq	%r12
	.cfi_def_cfa_offset 16
.LVL157:
	popq	%r13
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE43:
	.size	sort_words, .-sort_words
	.globl	insert_string
	.type	insert_string, @function
insert_string:
.LFB44:
	.loc 1 284 0
	.cfi_startproc
.LVL158:
	pushq	%r12
	.cfi_def_cfa_offset 16
	.cfi_offset 12, -16
	pushq	%rbp
	.cfi_def_cfa_offset 24
	.cfi_offset 6, -24
	pushq	%rbx
	.cfi_def_cfa_offset 32
	.cfi_offset 3, -32
	movq	%rdi, %rbx
	movq	%rsi, %rbp
	movq	%rcx, %r12
	.loc 1 286 0
	call	*%rdx
.LVL159:
	.loc 1 287 0
	movq	%rbx, %rdi
	call	*%rbp
.LVL160:
	.loc 1 288 0
	cltq
	movq	htable(%rip), %rdx
	leaq	(%rdx,%rax,8), %rbp
.LVL161:
	movq	%rbx, %rsi
	movq	0(%rbp), %rdi
	call	*%r12
.LVL162:
	movq	%rax, 0(%rbp)
	.loc 1 289 0
	popq	%rbx
	.cfi_def_cfa_offset 24
.LVL163:
	popq	%rbp
	.cfi_def_cfa_offset 16
	popq	%r12
	.cfi_def_cfa_offset 8
.LVL164:
	ret
	.cfi_endproc
.LFE44:
	.size	insert_string, .-insert_string
	.globl	init_token
	.type	init_token, @function
init_token:
.LFB45:
	.loc 1 297 0
	.cfi_startproc
.LVL165:
	.loc 1 298 0
	movl	$0, bufvalid(%rip)
	.loc 1 299 0
	movq	%rdi, infile(%rip)
	ret
	.cfi_endproc
.LFE45:
	.size	init_token, .-init_token
	.section	.rodata
	.align 8
.LC3:
	.string	" \t\n\r.,:;/<>()[]{}?!\"-'"
	.string	"xc2"
	.string	"xa0"
	.text
	.globl	get_word
	.type	get_word, @function
get_word:
.LFB46:
	.loc 1 307 0
	.cfi_startproc
	subq	$8, %rsp
	.cfi_def_cfa_offset 16
.L98:
	.loc 1 310 0
	cmpl	$0, bufvalid(%rip)
	je	.L95
	.loc 1 311 0
	movl	$.LC3, %esi
	movl	$0, %edi
	call	strtok
.LVL166:
	.loc 1 312 0
	testq	%rax, %rax
	jne	.L96
.LVL167:
.L95:
.LBB53:
.LBB54:
	.loc 2 263 0
	movq	infile(%rip), %rdx
	movl	$1024, %esi
	movl	$buf, %edi
	call	fgets
.LVL168:
.LBE54:
.LBE53:
	.loc 1 315 0
	testq	%rax, %rax
	je	.L97
	.loc 1 317 0
	movl	$1, bufvalid(%rip)
	.loc 1 318 0
	movl	$.LC3, %esi
	movl	$buf, %edi
	call	strtok
.LVL169:
	.loc 1 319 0
	testq	%rax, %rax
	je	.L98
.L96:
	.loc 1 322 0
	addl	$1, wcnt(%rip)
.LVL170:
.L97:
	.loc 1 324 0
	addq	$8, %rsp
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE46:
	.size	get_word, .-get_word
	.globl	get_token
	.type	get_token, @function
get_token:
.LFB47:
	.loc 1 330 0
	.cfi_startproc
.LVL171:
	pushq	%r15
	.cfi_def_cfa_offset 16
	.cfi_offset 15, -16
	pushq	%r14
	.cfi_def_cfa_offset 24
	.cfi_offset 14, -24
	pushq	%r13
	.cfi_def_cfa_offset 32
	.cfi_offset 13, -32
	pushq	%r12
	.cfi_def_cfa_offset 40
	.cfi_offset 12, -40
	pushq	%rbp
	.cfi_def_cfa_offset 48
	.cfi_offset 6, -48
	pushq	%rbx
	.cfi_def_cfa_offset 56
	.cfi_offset 3, -56
	subq	$8, %rsp
	.cfi_def_cfa_offset 64
.LVL172:
	.loc 1 339 0
	cmpl	$1, %edi
	jne	.L102
	.loc 1 340 0
	movl	$0, %eax
	call	get_word
.LVL173:
	jmp	.L103
.LVL174:
.L102:
	movl	%edi, %ebp
	.loc 1 341 0
	cmpl	$0, first.3687(%rip)
	je	.L104
	.loc 1 343 0
	leal	-1(%rdi), %r12d
	cmpl	bindex.3688(%rip), %r12d
	jle	.L105
.LVL175:
.L117:
.LBB55:
	.loc 1 344 0
	movl	$0, %eax
	call	get_word
.LVL176:
	.loc 1 345 0
	testq	%rax, %rax
	je	.L113
	.loc 1 348 0
	movl	bindex.3688(%rip), %edi
	leal	1(%rdi), %ebx
	movl	%ebx, bindex.3688(%rip)
.LVL177:
.LBB56:
.LBB57:
	.loc 3 110 0
	movslq	%edi, %rdi
	salq	$10, %rdi
.LVL178:
	addq	$token_buf.3686, %rdi
.LVL179:
	movq	%rax, %rsi
	call	strcpy
.LVL180:
.LBE57:
.LBE56:
.LBE55:
	.loc 1 343 0
	cmpl	%r12d, %ebx
	jl	.L117
.LVL181:
.L105:
	.loc 1 350 0
	movl	$0, first.3687(%rip)
.L104:
	.loc 1 353 0
	movl	$0, %eax
	call	get_word
.LVL182:
	.loc 1 354 0
	testq	%rax, %rax
	je	.L114
	.loc 1 357 0
	movl	bindex.3688(%rip), %edx
	leal	1(%rdx), %ebx
	movl	%ebx, bindex.3688(%rip)
.LVL183:
.LBB58:
.LBB59:
	.loc 3 110 0
	movslq	%edx, %rdi
	movq	%rdi, %rdx
	salq	$10, %rdx
.LVL184:
	leaq	token_buf.3686(%rdx), %rdi
.LVL185:
	movq	%rax, %rsi
	call	strcpy
.LVL186:
.LBE59:
.LBE58:
	.loc 1 358 0
	cmpl	$9, %ebx
	jle	.L107
	.loc 1 359 0
	movl	$0, bindex.3688(%rip)
.L107:
.LVL187:
	.loc 1 362 0
	movl	bindex.3688(%rip), %r12d
	subl	%ebp, %r12d
.LVL188:
	.loc 1 363 0
	leal	10(%r12), %eax
	testl	%r12d, %r12d
	cmovs	%eax, %r12d
.LVL189:
	.loc 1 364 0
	movl	$0, %r13d
	movl	$sbuf.3689, %r14d
	.loc 1 372 0
	movl	$0, %r15d
	.loc 1 377 0
	movq	%r14, %rax
	.loc 1 364 0
	testl	%ebp, %ebp
	jle	.L103
	jmp	.L119
.LVL190:
.L112:
	.loc 1 365 0
	testl	%r13d, %r13d
	je	.L116
	.loc 1 366 0
	leaq	1(%rax), %r14
.LVL191:
	movb	$32, (%rax)
	jmp	.L119
.LVL192:
.L116:
	.loc 1 369 0
	movq	%rax, %r14
.LVL193:
.L119:
	.loc 1 367 0
	movslq	%r12d, %rbx
	salq	$10, %rbx
	addq	$token_buf.3686, %rbx
.LVL194:
.LBB60:
.LBB61:
	.loc 3 110 0
	movq	%rbx, %rsi
	movq	%r14, %rdi
	call	strcpy
.LVL195:
.LBE61:
.LBE60:
	.loc 1 369 0
	movq	%rbx, %rdi
	call	Strlen
.LVL196:
	addq	%r14, %rax
.LVL197:
	.loc 1 370 0
	addl	$1, %r12d
.LVL198:
	.loc 1 372 0
	cmpl	$10, %r12d
	cmovge	%r15d, %r12d
.LVL199:
	.loc 1 364 0
	addl	$1, %r13d
.LVL200:
	cmpl	%r13d, %ebp
	jne	.L112
	.loc 1 377 0
	movl	$sbuf.3689, %eax
.LVL201:
	jmp	.L103
.LVL202:
.L113:
.LBB62:
	.loc 1 346 0
	movl	$0, %eax
.LVL203:
	jmp	.L103
.LVL204:
.L114:
.LBE62:
	.loc 1 355 0
	movl	$0, %eax
.LVL205:
.L103:
	.loc 1 378 0
	addq	$8, %rsp
	.cfi_def_cfa_offset 56
	popq	%rbx
	.cfi_def_cfa_offset 48
	popq	%rbp
	.cfi_def_cfa_offset 40
	popq	%r12
	.cfi_def_cfa_offset 32
	popq	%r13
	.cfi_def_cfa_offset 24
	popq	%r14
	.cfi_def_cfa_offset 16
	popq	%r15
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE47:
	.size	get_token, .-get_token
	.section	.rodata.str1.1,"aMS",@progbits,1
.LC4:
	.string	"%d\t'%s'\n"
	.section	.rodata.str1.8
	.align 8
.LC5:
	.string	"%d n-grams, %d unique, %d singletons.  Most common (%d) = '%s'.  Longest (%d have length %d) = '%s'\n"
	.text
	.globl	word_freq
	.type	word_freq, @function
word_freq:
.LFB48:
	.loc 1 386 0
	.cfi_startproc
.LVL206:
	pushq	%r15
	.cfi_def_cfa_offset 16
	.cfi_offset 15, -16
	pushq	%r14
	.cfi_def_cfa_offset 24
	.cfi_offset 14, -24
	pushq	%r13
	.cfi_def_cfa_offset 32
	.cfi_offset 13, -32
	pushq	%r12
	.cfi_def_cfa_offset 40
	.cfi_offset 12, -40
	pushq	%rbp
	.cfi_def_cfa_offset 48
	.cfi_offset 6, -48
	pushq	%rbx
	.cfi_def_cfa_offset 56
	.cfi_offset 3, -56
	subq	$24, %rsp
	.cfi_def_cfa_offset 80
	movl	%esi, %ebx
	movl	%edx, %ebp
	movl	%ecx, %r15d
	movl	%r8d, 12(%rsp)
	movq	%r9, %r14
	movq	80(%rsp), %r13
	movq	88(%rsp), %r12
	.loc 1 390 0
	call	init_token
.LVL207:
	.loc 1 391 0
	movl	%r15d, %edi
	call	new_table
.LVL208:
	.loc 1 393 0
	jmp	.L122
.LVL209:
.L123:
	.loc 1 394 0
	movq	%r12, %rcx
	movq	%r13, %rdx
	movq	%r14, %rsi
	movq	%rax, %rdi
	call	insert_string
.LVL210:
.L122:
	.loc 1 393 0
	movl	%ebp, %edi
	call	get_token
.LVL211:
	testq	%rax, %rax
	jne	.L123
	.loc 1 396 0
	testl	%ebx, %ebx
	jle	.L124
	.loc 1 397 0
	movl	12(%rsp), %edi
	call	sort_words
.LVL212:
	movq	%rax, %rbp
.LVL213:
	.loc 1 398 0
	testq	%rax, %rax
	je	.L124
	subl	$1, %ebx
.LVL214:
.L125:
.LBB63:
.LBB64:
	.loc 2 104 0
	movl	8(%rbp), %edx
	movq	0(%rbp), %rcx
	movl	$.LC4, %esi
	movl	$1, %edi
	movl	$0, %eax
	call	__printf_chk
.LVL215:
.LBE64:
.LBE63:
	.loc 1 400 0
	movq	16(%rbp), %rbp
.LVL216:
	.loc 1 398 0
	testq	%rbp, %rbp
	je	.L124
	.loc 1 398 0 is_stmt 0 discriminator 1
	subl	$1, %ebx
.LVL217:
	cmpl	$-1, %ebx
	jne	.L125
.LVL218:
.L124:
.LBB65:
.LBB66:
	.loc 2 104 0 is_stmt 1
	pushq	lstring(%rip)
	.cfi_def_cfa_offset 88
	movl	llen(%rip), %eax
	pushq	%rax
	.cfi_def_cfa_offset 96
	movl	lcnt(%rip), %eax
	pushq	%rax
	.cfi_def_cfa_offset 104
	pushq	mstring(%rip)
	.cfi_def_cfa_offset 112
	movl	mcnt(%rip), %r9d
	movl	scnt(%rip), %r8d
	movl	ucnt(%rip), %ecx
	movl	wcnt(%rip), %edx
	movl	$.LC5, %esi
	movl	$1, %edi
	movl	$0, %eax
	call	__printf_chk
.LVL219:
.LBE66:
.LBE65:
	.loc 1 405 0
	addq	$56, %rsp
	.cfi_def_cfa_offset 56
	popq	%rbx
	.cfi_def_cfa_offset 48
.LVL220:
	popq	%rbp
	.cfi_def_cfa_offset 40
	popq	%r12
	.cfi_def_cfa_offset 32
	popq	%r13
	.cfi_def_cfa_offset 24
	popq	%r14
	.cfi_def_cfa_offset 16
.LVL221:
	popq	%r15
	.cfi_def_cfa_offset 8
.LVL222:
	ret
	.cfi_endproc
.LFE48:
	.size	word_freq, .-word_freq
	.section	.rodata.str1.1
.LC6:
	.string	"verbose"
.LC7:
	.string	"size"
.LC8:
	.string	"hash"
.LC9:
	.string	"lower"
.LC10:
	.string	"find"
.LC11:
	.string	"ngram"
.LC12:
	.string	"quicksort"
.LC13:
	.string	"file"
.LC14:
	.string	"N-gram size %d\n"
.LC15:
	.string	"Lower case function %s\n"
.LC16:
	.string	"Hash function %s\n"
.LC17:
	.string	"Find element function %s\n"
	.section	.rodata.str1.8
	.align 8
.LC18:
	.string	"Invalid hash function index %d\n"
	.align 8
.LC19:
	.string	"Invalid lower function index %d\n"
	.align 8
.LC20:
	.string	"Invalid find function index %d\n"
	.section	.rodata.str1.1
.LC21:
	.string	"r"
.LC22:
	.string	"Couldn't open file '%s'\n"
.LC24:
	.string	"Total time = %f seconds\n"
	.text
	.globl	main
	.type	main, @function
main:
.LFB49:
	.loc 1 408 0
	.cfi_startproc
.LVL223:
	pushq	%r12
	.cfi_def_cfa_offset 16
	.cfi_offset 12, -16
	pushq	%rbp
	.cfi_def_cfa_offset 24
	.cfi_offset 6, -24
	pushq	%rbx
	.cfi_def_cfa_offset 32
	.cfi_offset 3, -32
	subq	$48, %rsp
	.cfi_def_cfa_offset 80
	movl	%edi, %ebp
	movq	%rsi, %r12
	.loc 1 408 0
	movq	%fs:40, %rax
	movq	%rax, 40(%rsp)
	xorl	%eax, %eax
	.loc 1 409 0
	movl	$1, 4(%rsp)
	.loc 1 410 0
	movl	$1024, 8(%rsp)
	.loc 1 411 0
	movl	$0, 12(%rsp)
	.loc 1 412 0
	movl	$0, 16(%rsp)
	.loc 1 413 0
	movl	$0, 20(%rsp)
	.loc 1 414 0
	movl	$1, 24(%rsp)
	.loc 1 415 0
	movl	$0, 28(%rsp)
	.loc 1 416 0
	movq	$0, 32(%rsp)
	.loc 1 417 0
	movq	stdin(%rip), %rbx
.LVL224:
	.loc 1 418 0
	leaq	4(%rsp), %rsi
.LVL225:
	movl	$.LC6, %edi
.LVL226:
	call	add_int_option
.LVL227:
	.loc 1 419 0
	leaq	8(%rsp), %rsi
	movl	$.LC7, %edi
	call	add_int_option
.LVL228:
	.loc 1 420 0
	leaq	12(%rsp), %rsi
	movl	$.LC8, %edi
	call	add_int_option
.LVL229:
	.loc 1 421 0
	leaq	16(%rsp), %rsi
	movl	$.LC9, %edi
	call	add_int_option
.LVL230:
	.loc 1 422 0
	leaq	20(%rsp), %rsi
	movl	$.LC10, %edi
	call	add_int_option
.LVL231:
	.loc 1 423 0
	leaq	24(%rsp), %rsi
	movl	$.LC11, %edi
	call	add_int_option
.LVL232:
	.loc 1 424 0
	leaq	28(%rsp), %rsi
	movl	$.LC12, %edi
	call	add_int_option
.LVL233:
	.loc 1 425 0
	leaq	32(%rsp), %rsi
	movl	$.LC13, %edi
	call	add_string_option
.LVL234:
	.loc 1 426 0
	movl	$0, %edx
	movq	%r12, %rsi
	movl	%ebp, %edi
	call	parse_options
.LVL235:
	.loc 1 427 0
	movq	stdout(%rip), %rdi
	call	show_options
.LVL236:
.LBB67:
.LBB68:
	.loc 2 104 0
	movl	24(%rsp), %edx
	movl	$.LC14, %esi
	movl	$1, %edi
	movl	$0, %eax
	call	__printf_chk
.LVL237:
.LBE68:
.LBE67:
	.loc 1 429 0
	movslq	16(%rsp), %rax
.LBB69:
.LBB70:
	.loc 2 104 0
	movq	lower_fun_names(,%rax,8), %rdx
	movl	$.LC15, %esi
	movl	$1, %edi
	movl	$0, %eax
	call	__printf_chk
.LVL238:
.LBE70:
.LBE69:
	.loc 1 430 0
	movslq	12(%rsp), %rax
.LBB71:
.LBB72:
	.loc 2 104 0
	movq	hash_fun_names(,%rax,8), %rdx
	movl	$.LC16, %esi
	movl	$1, %edi
	movl	$0, %eax
	call	__printf_chk
.LVL239:
.LBE72:
.LBE71:
	.loc 1 431 0
	movslq	20(%rsp), %rax
.LBB73:
.LBB74:
	.loc 2 104 0
	movq	find_ele_fun_names(,%rax,8), %rdx
	movl	$.LC17, %esi
	movl	$1, %edi
	movl	$0, %eax
	call	__printf_chk
.LVL240:
.LBE74:
.LBE73:
	.loc 1 432 0
	movl	12(%rsp), %ecx
	cmpl	$2, %ecx
	jbe	.L129
.LVL241:
.LBB75:
.LBB76:
	.loc 2 97 0
	movl	$.LC18, %edx
	movl	$1, %esi
	movq	stderr(%rip), %rdi
	movl	$0, %eax
	call	__fprintf_chk
.LVL242:
.LBE76:
.LBE75:
	.loc 1 434 0
	movl	$1, %edi
	call	exit
.LVL243:
.L129:
	.loc 1 436 0
	movl	16(%rsp), %ecx
	cmpl	$1, %ecx
	jbe	.L130
.LVL244:
.LBB77:
.LBB78:
	.loc 2 97 0
	movl	$.LC19, %edx
	movl	$1, %esi
	movq	stderr(%rip), %rdi
	movl	$0, %eax
	call	__fprintf_chk
.LVL245:
.LBE78:
.LBE77:
	.loc 1 438 0
	movl	$1, %edi
	call	exit
.LVL246:
.L130:
	.loc 1 440 0
	movl	20(%rsp), %ecx
	cmpl	$2, %ecx
	jbe	.L131
.LVL247:
.LBB79:
.LBB80:
	.loc 2 97 0
	movl	$.LC20, %edx
	movl	$1, %esi
	movq	stderr(%rip), %rdi
	movl	$0, %eax
	call	__fprintf_chk
.LVL248:
.LBE80:
.LBE79:
	.loc 1 442 0
	movl	$1, %edi
	call	exit
.LVL249:
.L131:
	.loc 1 444 0
	movq	32(%rsp), %rdi
	testq	%rdi, %rdi
	je	.L132
	.loc 1 445 0
	movl	$.LC21, %esi
	call	fopen
.LVL250:
	movq	%rax, %rbx
.LVL251:
	.loc 1 446 0
	testq	%rax, %rax
	jne	.L132
.LVL252:
.LBB81:
.LBB82:
	.loc 2 97 0
	movq	32(%rsp), %rcx
	movl	$.LC22, %edx
	movl	$1, %esi
	movq	stderr(%rip), %rdi
	movl	$0, %eax
.LVL253:
	call	__fprintf_chk
.LVL254:
.LBE82:
.LBE81:
	.loc 1 448 0
	movl	$1, %edi
	call	exit
.LVL255:
.L132:
	.loc 1 451 0
	movslq	12(%rsp), %rax
	movslq	20(%rsp), %rdx
	pushq	find_ele_fun_set(,%rdx,8)
	.cfi_def_cfa_offset 88
	movslq	24(%rsp), %rdx
	pushq	lower_fun_set(,%rdx,8)
	.cfi_def_cfa_offset 96
	movq	hash_fun_set(,%rax,8), %r9
	movl	44(%rsp), %r8d
	movl	24(%rsp), %ecx
	movl	40(%rsp), %edx
	movl	20(%rsp), %esi
	movq	%rbx, %rdi
	call	word_freq
.LVL256:
	.loc 1 455 0
	call	clock
.LVL257:
.LBB83:
.LBB84:
	.loc 2 104 0
	pxor	%xmm0, %xmm0
	cvtsi2sdq	%rax, %xmm0
	divsd	.LC23(%rip), %xmm0
	movl	$.LC24, %esi
	movl	$1, %edi
	movl	$1, %eax
	call	__printf_chk
.LVL258:
.LBE84:
.LBE83:
	.loc 1 456 0
	movq	%rbx, %rdi
	call	fclose
.LVL259:
	addq	$16, %rsp
	.cfi_def_cfa_offset 80
	.loc 1 458 0
	movl	$0, %eax
	movq	40(%rsp), %rbx
	xorq	%fs:40, %rbx
.LVL260:
	je	.L133
	call	__stack_chk_fail
.LVL261:
.L133:
	addq	$48, %rsp
	.cfi_def_cfa_offset 32
	popq	%rbx
	.cfi_def_cfa_offset 24
	popq	%rbp
	.cfi_def_cfa_offset 16
.LVL262:
	popq	%r12
	.cfi_def_cfa_offset 8
.LVL263:
	ret
	.cfi_endproc
.LFE49:
	.size	main, .-main
	.local	token_buf.3686
	.comm	token_buf.3686,10240,32
	.local	bindex.3688
	.comm	bindex.3688,4,4
	.data
	.align 4
	.type	first.3687, @object
	.size	first.3687, 4
first.3687:
	.long	1
	.local	sbuf.3689
	.comm	sbuf.3689,1024,32
	.comm	infile,8,8
	.globl	bufvalid
	.bss
	.align 4
	.type	bufvalid, @object
	.size	bufvalid, 4
bufvalid:
	.zero	4
	.comm	buf,1024,32
	.globl	find_ele_fun_names
	.section	.rodata.str1.1
.LC25:
	.string	"find_ele_rec"
.LC26:
	.string	"find_ele_iter_f"
.LC27:
	.string	"find_ele_iter_r"
	.data
	.align 16
	.type	find_ele_fun_names, @object
	.size	find_ele_fun_names, 24
find_ele_fun_names:
	.quad	.LC25
	.quad	.LC26
	.quad	.LC27
	.globl	find_ele_fun_set
	.align 16
	.type	find_ele_fun_set, @object
	.size	find_ele_fun_set, 24
find_ele_fun_set:
	.quad	find_ele_rec
	.quad	find_ele_iter_f
	.quad	find_ele_iter_r
	.globl	hash_fun_names
	.section	.rodata.str1.1
.LC28:
	.string	"h_mod"
.LC29:
	.string	"h_add"
.LC30:
	.string	"h_xor"
	.data
	.align 16
	.type	hash_fun_names, @object
	.size	hash_fun_names, 24
hash_fun_names:
	.quad	.LC28
	.quad	.LC29
	.quad	.LC30
	.globl	hash_fun_set
	.align 16
	.type	hash_fun_set, @object
	.size	hash_fun_set, 24
hash_fun_set:
	.quad	h_mod
	.quad	h_add
	.quad	h_xor
	.comm	tsize,4,4
	.comm	htable,8,8
	.globl	lower_fun_names
	.section	.rodata.str1.1
.LC31:
	.string	"lower1"
.LC32:
	.string	"lower2"
	.data
	.align 16
	.type	lower_fun_names, @object
	.size	lower_fun_names, 16
lower_fun_names:
	.quad	.LC31
	.quad	.LC32
	.globl	lower_fun_set
	.align 16
	.type	lower_fun_set, @object
	.size	lower_fun_set, 16
lower_fun_set:
	.quad	lower1
	.quad	lower2
	.globl	lcnt
	.bss
	.align 4
	.type	lcnt, @object
	.size	lcnt, 4
lcnt:
	.zero	4
	.globl	lstring
	.section	.rodata.str1.1
.LC33:
	.string	""
	.data
	.align 8
	.type	lstring, @object
	.size	lstring, 8
lstring:
	.quad	.LC33
	.globl	llen
	.bss
	.align 4
	.type	llen, @object
	.size	llen, 4
llen:
	.zero	4
	.globl	mstring
	.data
	.align 8
	.type	mstring, @object
	.size	mstring, 8
mstring:
	.quad	.LC33
	.globl	scnt
	.bss
	.align 4
	.type	scnt, @object
	.size	scnt, 4
scnt:
	.zero	4
	.globl	mcnt
	.align 4
	.type	mcnt, @object
	.size	mcnt, 4
mcnt:
	.zero	4
	.globl	ucnt
	.align 4
	.type	ucnt, @object
	.size	ucnt, 4
ucnt:
	.zero	4
	.globl	wcnt
	.align 4
	.type	wcnt, @object
	.size	wcnt, 4
wcnt:
	.zero	4
	.section	.rodata.cst8,"aM",@progbits,8
	.align 8
.LC23:
	.long	0
	.long	1093567616
	.text
.Letext0:
	.file 4 "/usr/lib/gcc/x86_64-linux-gnu/5/include/stddef.h"
	.file 5 "/usr/include/x86_64-linux-gnu/bits/types.h"
	.file 6 "/usr/include/stdio.h"
	.file 7 "/usr/include/libio.h"
	.file 8 "/usr/include/time.h"
	.file 9 "/usr/include/stdlib.h"
	.file 10 "<built-in>"
	.file 11 "/usr/include/string.h"
	.file 12 "options.h"
	.section	.debug_info,"",@progbits
.Ldebug_info0:
	.long	0x1a5f
	.value	0x4
	.long	.Ldebug_abbrev0
	.byte	0x8
	.uleb128 0x1
	.long	.LASF154
	.byte	0xc
	.long	.LASF155
	.long	.LASF156
	.quad	.Ltext0
	.quad	.Letext0-.Ltext0
	.long	.Ldebug_line0
	.uleb128 0x2
	.long	.LASF7
	.byte	0x4
	.byte	0xd8
	.long	0x38
	.uleb128 0x3
	.byte	0x8
	.byte	0x7
	.long	.LASF0
	.uleb128 0x3
	.byte	0x1
	.byte	0x8
	.long	.LASF1
	.uleb128 0x3
	.byte	0x2
	.byte	0x7
	.long	.LASF2
	.uleb128 0x3
	.byte	0x4
	.byte	0x7
	.long	.LASF3
	.uleb128 0x3
	.byte	0x1
	.byte	0x6
	.long	.LASF4
	.uleb128 0x3
	.byte	0x2
	.byte	0x5
	.long	.LASF5
	.uleb128 0x4
	.byte	0x4
	.byte	0x5
	.string	"int"
	.uleb128 0x3
	.byte	0x8
	.byte	0x5
	.long	.LASF6
	.uleb128 0x2
	.long	.LASF8
	.byte	0x5
	.byte	0x83
	.long	0x69
	.uleb128 0x2
	.long	.LASF9
	.byte	0x5
	.byte	0x84
	.long	0x69
	.uleb128 0x3
	.byte	0x8
	.byte	0x7
	.long	.LASF10
	.uleb128 0x2
	.long	.LASF11
	.byte	0x5
	.byte	0x87
	.long	0x69
	.uleb128 0x5
	.byte	0x8
	.uleb128 0x6
	.byte	0x8
	.long	0xa0
	.uleb128 0x3
	.byte	0x1
	.byte	0x6
	.long	.LASF12
	.uleb128 0x2
	.long	.LASF13
	.byte	0x6
	.byte	0x30
	.long	0xb2
	.uleb128 0x7
	.long	.LASF43
	.byte	0xd8
	.byte	0x7
	.byte	0xf1
	.long	0x22f
	.uleb128 0x8
	.long	.LASF14
	.byte	0x7
	.byte	0xf2
	.long	0x62
	.byte	0
	.uleb128 0x8
	.long	.LASF15
	.byte	0x7
	.byte	0xf7
	.long	0x9a
	.byte	0x8
	.uleb128 0x8
	.long	.LASF16
	.byte	0x7
	.byte	0xf8
	.long	0x9a
	.byte	0x10
	.uleb128 0x8
	.long	.LASF17
	.byte	0x7
	.byte	0xf9
	.long	0x9a
	.byte	0x18
	.uleb128 0x8
	.long	.LASF18
	.byte	0x7
	.byte	0xfa
	.long	0x9a
	.byte	0x20
	.uleb128 0x8
	.long	.LASF19
	.byte	0x7
	.byte	0xfb
	.long	0x9a
	.byte	0x28
	.uleb128 0x8
	.long	.LASF20
	.byte	0x7
	.byte	0xfc
	.long	0x9a
	.byte	0x30
	.uleb128 0x8
	.long	.LASF21
	.byte	0x7
	.byte	0xfd
	.long	0x9a
	.byte	0x38
	.uleb128 0x8
	.long	.LASF22
	.byte	0x7
	.byte	0xfe
	.long	0x9a
	.byte	0x40
	.uleb128 0x9
	.long	.LASF23
	.byte	0x7
	.value	0x100
	.long	0x9a
	.byte	0x48
	.uleb128 0x9
	.long	.LASF24
	.byte	0x7
	.value	0x101
	.long	0x9a
	.byte	0x50
	.uleb128 0x9
	.long	.LASF25
	.byte	0x7
	.value	0x102
	.long	0x9a
	.byte	0x58
	.uleb128 0x9
	.long	.LASF26
	.byte	0x7
	.value	0x104
	.long	0x267
	.byte	0x60
	.uleb128 0x9
	.long	.LASF27
	.byte	0x7
	.value	0x106
	.long	0x26d
	.byte	0x68
	.uleb128 0x9
	.long	.LASF28
	.byte	0x7
	.value	0x108
	.long	0x62
	.byte	0x70
	.uleb128 0x9
	.long	.LASF29
	.byte	0x7
	.value	0x10c
	.long	0x62
	.byte	0x74
	.uleb128 0x9
	.long	.LASF30
	.byte	0x7
	.value	0x10e
	.long	0x70
	.byte	0x78
	.uleb128 0x9
	.long	.LASF31
	.byte	0x7
	.value	0x112
	.long	0x46
	.byte	0x80
	.uleb128 0x9
	.long	.LASF32
	.byte	0x7
	.value	0x113
	.long	0x54
	.byte	0x82
	.uleb128 0x9
	.long	.LASF33
	.byte	0x7
	.value	0x114
	.long	0x273
	.byte	0x83
	.uleb128 0x9
	.long	.LASF34
	.byte	0x7
	.value	0x118
	.long	0x283
	.byte	0x88
	.uleb128 0x9
	.long	.LASF35
	.byte	0x7
	.value	0x121
	.long	0x7b
	.byte	0x90
	.uleb128 0x9
	.long	.LASF36
	.byte	0x7
	.value	0x129
	.long	0x98
	.byte	0x98
	.uleb128 0x9
	.long	.LASF37
	.byte	0x7
	.value	0x12a
	.long	0x98
	.byte	0xa0
	.uleb128 0x9
	.long	.LASF38
	.byte	0x7
	.value	0x12b
	.long	0x98
	.byte	0xa8
	.uleb128 0x9
	.long	.LASF39
	.byte	0x7
	.value	0x12c
	.long	0x98
	.byte	0xb0
	.uleb128 0x9
	.long	.LASF40
	.byte	0x7
	.value	0x12e
	.long	0x2d
	.byte	0xb8
	.uleb128 0x9
	.long	.LASF41
	.byte	0x7
	.value	0x12f
	.long	0x62
	.byte	0xc0
	.uleb128 0x9
	.long	.LASF42
	.byte	0x7
	.value	0x131
	.long	0x289
	.byte	0xc4
	.byte	0
	.uleb128 0xa
	.long	.LASF157
	.byte	0x7
	.byte	0x96
	.uleb128 0x7
	.long	.LASF44
	.byte	0x18
	.byte	0x7
	.byte	0x9c
	.long	0x267
	.uleb128 0x8
	.long	.LASF45
	.byte	0x7
	.byte	0x9d
	.long	0x267
	.byte	0
	.uleb128 0x8
	.long	.LASF46
	.byte	0x7
	.byte	0x9e
	.long	0x26d
	.byte	0x8
	.uleb128 0x8
	.long	.LASF47
	.byte	0x7
	.byte	0xa2
	.long	0x62
	.byte	0x10
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x236
	.uleb128 0x6
	.byte	0x8
	.long	0xb2
	.uleb128 0xb
	.long	0xa0
	.long	0x283
	.uleb128 0xc
	.long	0x86
	.byte	0
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x22f
	.uleb128 0xb
	.long	0xa0
	.long	0x299
	.uleb128 0xc
	.long	0x86
	.byte	0x13
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x29f
	.uleb128 0xd
	.long	0xa0
	.uleb128 0x3
	.byte	0x8
	.byte	0x5
	.long	.LASF48
	.uleb128 0x2
	.long	.LASF49
	.byte	0x8
	.byte	0x3b
	.long	0x8d
	.uleb128 0x3
	.byte	0x8
	.byte	0x7
	.long	.LASF50
	.uleb128 0x6
	.byte	0x8
	.long	0x2c3
	.uleb128 0xe
	.uleb128 0x2
	.long	.LASF51
	.byte	0x1
	.byte	0x18
	.long	0x2cf
	.uleb128 0x6
	.byte	0x8
	.long	0x2d5
	.uleb128 0xf
	.long	0x2e0
	.uleb128 0x10
	.long	0x9a
	.byte	0
	.uleb128 0x7
	.long	.LASF52
	.byte	0x18
	.byte	0x1
	.byte	0x48
	.long	0x311
	.uleb128 0x8
	.long	.LASF53
	.byte	0x1
	.byte	0x49
	.long	0x9a
	.byte	0
	.uleb128 0x8
	.long	.LASF54
	.byte	0x1
	.byte	0x4a
	.long	0x62
	.byte	0x8
	.uleb128 0x8
	.long	.LASF55
	.byte	0x1
	.byte	0x4b
	.long	0x311
	.byte	0x10
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x2e0
	.uleb128 0x2
	.long	.LASF56
	.byte	0x1
	.byte	0x4c
	.long	0x311
	.uleb128 0x2
	.long	.LASF57
	.byte	0x1
	.byte	0x74
	.long	0x32d
	.uleb128 0x6
	.byte	0x8
	.long	0x333
	.uleb128 0x11
	.long	0x4d
	.long	0x342
	.uleb128 0x10
	.long	0x9a
	.byte	0
	.uleb128 0x2
	.long	.LASF58
	.byte	0x1
	.byte	0xe7
	.long	0x34d
	.uleb128 0x6
	.byte	0x8
	.long	0x353
	.uleb128 0x11
	.long	0x317
	.long	0x367
	.uleb128 0x10
	.long	0x317
	.uleb128 0x10
	.long	0x9a
	.byte	0
	.uleb128 0x12
	.long	.LASF61
	.byte	0x2
	.byte	0x5f
	.long	0x62
	.byte	0x3
	.long	0x38f
	.uleb128 0x13
	.long	.LASF59
	.byte	0x2
	.byte	0x5f
	.long	0x395
	.uleb128 0x13
	.long	.LASF60
	.byte	0x2
	.byte	0x5f
	.long	0x39a
	.uleb128 0x14
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0xa7
	.uleb128 0x15
	.long	0x38f
	.uleb128 0x15
	.long	0x299
	.uleb128 0x12
	.long	.LASF62
	.byte	0x3
	.byte	0x6c
	.long	0x9a
	.byte	0x3
	.long	0x3c6
	.uleb128 0x13
	.long	.LASF63
	.byte	0x3
	.byte	0x6c
	.long	0x3c6
	.uleb128 0x13
	.long	.LASF64
	.byte	0x3
	.byte	0x6c
	.long	0x39a
	.byte	0
	.uleb128 0x15
	.long	0x9a
	.uleb128 0x12
	.long	.LASF65
	.byte	0x2
	.byte	0xfd
	.long	0x9a
	.byte	0x3
	.long	0x3fd
	.uleb128 0x16
	.string	"__s"
	.byte	0x2
	.byte	0xfd
	.long	0x3c6
	.uleb128 0x16
	.string	"__n"
	.byte	0x2
	.byte	0xfd
	.long	0x62
	.uleb128 0x13
	.long	.LASF59
	.byte	0x2
	.byte	0xfd
	.long	0x395
	.byte	0
	.uleb128 0x12
	.long	.LASF66
	.byte	0x2
	.byte	0x66
	.long	0x62
	.byte	0x3
	.long	0x41a
	.uleb128 0x13
	.long	.LASF60
	.byte	0x2
	.byte	0x66
	.long	0x39a
	.uleb128 0x14
	.byte	0
	.uleb128 0x17
	.long	.LASF67
	.byte	0x1
	.byte	0x76
	.long	0x4d
	.quad	.LFB35
	.quad	.LFE35-.LFB35
	.uleb128 0x1
	.byte	0x9c
	.long	0x465
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0x76
	.long	0x9a
	.long	.LLST0
	.uleb128 0x19
	.string	"val"
	.byte	0x1
	.byte	0x78
	.long	0x4d
	.long	.LLST1
	.uleb128 0x19
	.string	"c"
	.byte	0x1
	.byte	0x79
	.long	0x62
	.long	.LLST2
	.byte	0
	.uleb128 0x17
	.long	.LASF68
	.byte	0x1
	.byte	0x80
	.long	0x4d
	.quad	.LFB36
	.quad	.LFE36-.LFB36
	.uleb128 0x1
	.byte	0x9c
	.long	0x4b0
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0x80
	.long	0x9a
	.long	.LLST3
	.uleb128 0x19
	.string	"val"
	.byte	0x1
	.byte	0x82
	.long	0x4d
	.long	.LLST4
	.uleb128 0x19
	.string	"c"
	.byte	0x1
	.byte	0x83
	.long	0x62
	.long	.LLST5
	.byte	0
	.uleb128 0x17
	.long	.LASF69
	.byte	0x1
	.byte	0x8a
	.long	0x4d
	.quad	.LFB37
	.quad	.LFE37-.LFB37
	.uleb128 0x1
	.byte	0x9c
	.long	0x4fb
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0x8a
	.long	0x9a
	.long	.LLST6
	.uleb128 0x19
	.string	"val"
	.byte	0x1
	.byte	0x8c
	.long	0x4d
	.long	.LLST7
	.uleb128 0x19
	.string	"c"
	.byte	0x1
	.byte	0x8d
	.long	0x62
	.long	.LLST8
	.byte	0
	.uleb128 0x17
	.long	.LASF70
	.byte	0x1
	.byte	0xf0
	.long	0x62
	.quad	.LFB42
	.quad	.LFE42-.LFB42
	.uleb128 0x1
	.byte	0x9c
	.long	0x553
	.uleb128 0x1a
	.long	.LASF71
	.byte	0x1
	.byte	0xf0
	.long	0x2bd
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1a
	.long	.LASF72
	.byte	0x1
	.byte	0xf0
	.long	0x2bd
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1b
	.long	.LASF73
	.byte	0x1
	.byte	0xf1
	.long	0x317
	.uleb128 0x2
	.byte	0x75
	.sleb128 0
	.uleb128 0x1b
	.long	.LASF74
	.byte	0x1
	.byte	0xf2
	.long	0x317
	.uleb128 0x2
	.byte	0x74
	.sleb128 0
	.byte	0
	.uleb128 0x1c
	.long	.LASF158
	.byte	0x1
	.byte	0x52
	.quad	.LFB33
	.quad	.LFE33-.LFB33
	.uleb128 0x1
	.byte	0x9c
	.long	0x607
	.uleb128 0x1d
	.long	.LASF75
	.byte	0x1
	.byte	0x52
	.long	0x62
	.long	.LLST9
	.uleb128 0x1e
	.long	0x367
	.quad	.LBB41
	.quad	.LBE41-.LBB41
	.byte	0x1
	.byte	0x57
	.long	0x5cf
	.uleb128 0x1f
	.long	0x382
	.long	.LLST10
	.uleb128 0x20
	.long	0x377
	.uleb128 0x21
	.quad	.LVL39
	.long	0x1975
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x2
	.byte	0x8
	.byte	0x26
	.byte	0
	.byte	0
	.uleb128 0x23
	.quad	.LVL37
	.long	0x1984
	.long	0x5f3
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x38
	.byte	0
	.uleb128 0x21
	.quad	.LVL40
	.long	0x1990
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.byte	0
	.byte	0
	.uleb128 0x17
	.long	.LASF76
	.byte	0x1
	.byte	0x38
	.long	0x2d
	.quad	.LFB32
	.quad	.LFE32-.LFB32
	.uleb128 0x1
	.byte	0x9c
	.long	0x636
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0x38
	.long	0x299
	.long	.LLST11
	.byte	0
	.uleb128 0x24
	.long	.LASF77
	.byte	0x1
	.byte	0x1d
	.quad	.LFB30
	.quad	.LFE30-.LFB30
	.uleb128 0x1
	.byte	0x9c
	.long	0x682
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0x1d
	.long	0x9a
	.long	.LLST12
	.uleb128 0x19
	.string	"i"
	.byte	0x1
	.byte	0x1f
	.long	0x62
	.long	.LLST13
	.uleb128 0x21
	.quad	.LVL47
	.long	0x607
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.byte	0
	.byte	0
	.uleb128 0x24
	.long	.LASF78
	.byte	0x1
	.byte	0x27
	.quad	.LFB31
	.quad	.LFE31-.LFB31
	.uleb128 0x1
	.byte	0x9c
	.long	0x6dd
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0x27
	.long	0x9a
	.long	.LLST14
	.uleb128 0x19
	.string	"i"
	.byte	0x1
	.byte	0x29
	.long	0x62
	.long	.LLST15
	.uleb128 0x19
	.string	"len"
	.byte	0x1
	.byte	0x2a
	.long	0x62
	.long	.LLST16
	.uleb128 0x21
	.quad	.LVL51
	.long	0x607
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.byte	0
	.uleb128 0x25
	.long	.LASF159
	.byte	0x1
	.byte	0x5c
	.long	0x317
	.quad	.LFB34
	.quad	.LFE34-.LFB34
	.uleb128 0x1
	.byte	0x9c
	.long	0x7bc
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0x5c
	.long	0x9a
	.long	.LLST17
	.uleb128 0x26
	.long	.LASF79
	.byte	0x1
	.byte	0x5e
	.long	0x317
	.long	.LLST18
	.uleb128 0x26
	.long	.LASF80
	.byte	0x1
	.byte	0x5f
	.long	0x62
	.long	.LLST19
	.uleb128 0x1e
	.long	0x367
	.quad	.LBB43
	.quad	.LBE43-.LBB43
	.byte	0x1
	.byte	0x67
	.long	0x779
	.uleb128 0x1f
	.long	0x382
	.long	.LLST20
	.uleb128 0x20
	.long	0x377
	.uleb128 0x21
	.quad	.LVL63
	.long	0x1975
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC1
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x2
	.byte	0x8
	.byte	0x28
	.byte	0
	.byte	0
	.uleb128 0x23
	.quad	.LVL59
	.long	0x199c
	.long	0x790
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x48
	.byte	0
	.uleb128 0x23
	.quad	.LVL61
	.long	0x607
	.long	0x7a8
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.byte	0
	.uleb128 0x21
	.quad	.LVL64
	.long	0x1990
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.byte	0
	.byte	0
	.uleb128 0x17
	.long	.LASF81
	.byte	0x1
	.byte	0x97
	.long	0x9a
	.quad	.LFB38
	.quad	.LFE38-.LFB38
	.uleb128 0x1
	.byte	0x9c
	.long	0x8ca
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0x97
	.long	0x9a
	.long	.LLST21
	.uleb128 0x26
	.long	.LASF79
	.byte	0x1
	.byte	0x99
	.long	0x9a
	.long	.LLST22
	.uleb128 0x1e
	.long	0x367
	.quad	.LBB45
	.quad	.LBE45-.LBB45
	.byte	0x1
	.byte	0x9b
	.long	0x849
	.uleb128 0x1f
	.long	0x382
	.long	.LLST23
	.uleb128 0x20
	.long	0x377
	.uleb128 0x21
	.quad	.LVL72
	.long	0x1975
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC2
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x2
	.byte	0x8
	.byte	0x2c
	.byte	0
	.byte	0
	.uleb128 0x1e
	.long	0x39f
	.quad	.LBB47
	.quad	.LBE47-.LBB47
	.byte	0x1
	.byte	0x9e
	.long	0x891
	.uleb128 0x1f
	.long	0x3ba
	.long	.LLST24
	.uleb128 0x1f
	.long	0x3af
	.long	.LLST25
	.uleb128 0x21
	.quad	.LVL75
	.long	0x19a8
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.byte	0
	.byte	0
	.uleb128 0x23
	.quad	.LVL69
	.long	0x607
	.long	0x8a9
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.byte	0
	.uleb128 0x27
	.quad	.LVL70
	.long	0x199c
	.uleb128 0x21
	.quad	.LVL73
	.long	0x1990
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.byte	0
	.byte	0
	.uleb128 0x17
	.long	.LASF82
	.byte	0x1
	.byte	0xa3
	.long	0x317
	.quad	.LFB39
	.quad	.LFE39-.LFB39
	.uleb128 0x1
	.byte	0x9c
	.long	0x958
	.uleb128 0x18
	.string	"ls"
	.byte	0x1
	.byte	0xa3
	.long	0x317
	.long	.LLST26
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0xa3
	.long	0x9a
	.long	.LLST27
	.uleb128 0x23
	.quad	.LVL80
	.long	0x7bc
	.long	0x91e
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.byte	0
	.uleb128 0x27
	.quad	.LVL81
	.long	0x6dd
	.uleb128 0x23
	.quad	.LVL85
	.long	0x19b7
	.long	0x943
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.byte	0
	.uleb128 0x21
	.quad	.LVL88
	.long	0x8ca
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.byte	0
	.byte	0
	.uleb128 0x17
	.long	.LASF83
	.byte	0x1
	.byte	0xb7
	.long	0x317
	.quad	.LFB40
	.quad	.LFE40-.LFB40
	.uleb128 0x1
	.byte	0x9c
	.long	0xa2d
	.uleb128 0x18
	.string	"ls"
	.byte	0x1
	.byte	0xb7
	.long	0x317
	.long	.LLST28
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0xb7
	.long	0x9a
	.long	.LLST29
	.uleb128 0x19
	.string	"ele"
	.byte	0x1
	.byte	0xb9
	.long	0x317
	.long	.LLST30
	.uleb128 0x28
	.quad	.LBB49
	.quad	.LBE49-.LBB49
	.long	0xa07
	.uleb128 0x26
	.long	.LASF53
	.byte	0x1
	.byte	0xbb
	.long	0x9a
	.long	.LLST31
	.uleb128 0x28
	.quad	.LBB50
	.quad	.LBE50-.LBB50
	.long	0x9ec
	.uleb128 0x26
	.long	.LASF54
	.byte	0x1
	.byte	0xbd
	.long	0x62
	.long	.LLST32
	.byte	0
	.uleb128 0x21
	.quad	.LVL95
	.long	0x19b7
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.byte	0
	.byte	0
	.uleb128 0x23
	.quad	.LVL101
	.long	0x7bc
	.long	0xa1f
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.byte	0
	.uleb128 0x27
	.quad	.LVL102
	.long	0x6dd
	.byte	0
	.uleb128 0x17
	.long	.LASF84
	.byte	0x1
	.byte	0xcc
	.long	0x317
	.quad	.LFB41
	.quad	.LFE41-.LFB41
	.uleb128 0x1
	.byte	0x9c
	.long	0xb36
	.uleb128 0x18
	.string	"ls"
	.byte	0x1
	.byte	0xcc
	.long	0x317
	.long	.LLST33
	.uleb128 0x18
	.string	"s"
	.byte	0x1
	.byte	0xcc
	.long	0x9a
	.long	.LLST34
	.uleb128 0x19
	.string	"ele"
	.byte	0x1
	.byte	0xce
	.long	0x317
	.long	.LLST35
	.uleb128 0x26
	.long	.LASF85
	.byte	0x1
	.byte	0xcf
	.long	0x317
	.long	.LLST36
	.uleb128 0x28
	.quad	.LBB51
	.quad	.LBE51-.LBB51
	.long	0xaeb
	.uleb128 0x26
	.long	.LASF53
	.byte	0x1
	.byte	0xd1
	.long	0x9a
	.long	.LLST37
	.uleb128 0x28
	.quad	.LBB52
	.quad	.LBE52-.LBB52
	.long	0xad0
	.uleb128 0x26
	.long	.LASF54
	.byte	0x1
	.byte	0xd3
	.long	0x62
	.long	.LLST38
	.byte	0
	.uleb128 0x21
	.quad	.LVL111
	.long	0x19b7
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.byte	0
	.byte	0
	.uleb128 0x23
	.quad	.LVL116
	.long	0x7bc
	.long	0xb03
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.byte	0
	.uleb128 0x27
	.quad	.LVL117
	.long	0x6dd
	.uleb128 0x23
	.quad	.LVL121
	.long	0x7bc
	.long	0xb28
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.byte	0
	.uleb128 0x27
	.quad	.LVL122
	.long	0x6dd
	.byte	0
	.uleb128 0x17
	.long	.LASF86
	.byte	0x1
	.byte	0xf8
	.long	0x317
	.quad	.LFB43
	.quad	.LFE43-.LFB43
	.uleb128 0x1
	.byte	0x9c
	.long	0xc1d
	.uleb128 0x1d
	.long	.LASF87
	.byte	0x1
	.byte	0xf8
	.long	0x62
	.long	.LLST39
	.uleb128 0x19
	.string	"ls"
	.byte	0x1
	.byte	0xfa
	.long	0x317
	.long	.LLST40
	.uleb128 0x19
	.string	"ele"
	.byte	0x1
	.byte	0xfb
	.long	0x317
	.long	.LLST41
	.uleb128 0x26
	.long	.LASF88
	.byte	0x1
	.byte	0xfc
	.long	0xc1d
	.long	.LLST42
	.uleb128 0x19
	.string	"i"
	.byte	0x1
	.byte	0xfd
	.long	0x62
	.long	.LLST43
	.uleb128 0x19
	.string	"j"
	.byte	0x1
	.byte	0xfd
	.long	0x62
	.long	.LLST44
	.uleb128 0x19
	.string	"cnt"
	.byte	0x1
	.byte	0xfe
	.long	0x62
	.long	.LLST45
	.uleb128 0x23
	.quad	.LVL128
	.long	0x1984
	.long	0xbd2
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x38
	.byte	0
	.uleb128 0x23
	.quad	.LVL148
	.long	0x19c2
	.long	0xc08
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x8
	.byte	0x76
	.sleb128 0
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x1
	.byte	0x38
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x52
	.uleb128 0x9
	.byte	0x3
	.quad	compare_ele
	.byte	0
	.uleb128 0x21
	.quad	.LVL155
	.long	0x19ce
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x317
	.uleb128 0x29
	.long	.LASF89
	.byte	0x1
	.value	0x119
	.quad	.LFB44
	.quad	.LFE44-.LFB44
	.uleb128 0x1
	.byte	0x9c
	.long	0xcd2
	.uleb128 0x2a
	.string	"s"
	.byte	0x1
	.value	0x119
	.long	0x9a
	.long	.LLST46
	.uleb128 0x2b
	.long	.LASF90
	.byte	0x1
	.value	0x11a
	.long	0x322
	.long	.LLST47
	.uleb128 0x2b
	.long	.LASF91
	.byte	0x1
	.value	0x11a
	.long	0x2c4
	.long	.LLST48
	.uleb128 0x2b
	.long	.LASF92
	.byte	0x1
	.value	0x11b
	.long	0x342
	.long	.LLST49
	.uleb128 0x2c
	.long	.LASF93
	.byte	0x1
	.value	0x11d
	.long	0x62
	.long	.LLST50
	.uleb128 0x2d
	.quad	.LVL159
	.uleb128 0x3
	.byte	0xf3
	.uleb128 0x1
	.byte	0x51
	.long	0xca7
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.uleb128 0x2d
	.quad	.LVL160
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.long	0xcbe
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.uleb128 0x2e
	.quad	.LVL162
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.byte	0
	.uleb128 0x29
	.long	.LASF94
	.byte	0x1
	.value	0x129
	.quad	.LFB45
	.quad	.LFE45-.LFB45
	.uleb128 0x1
	.byte	0x9c
	.long	0xcfe
	.uleb128 0x2f
	.string	"in"
	.byte	0x1
	.value	0x129
	.long	0x38f
	.uleb128 0x1
	.byte	0x55
	.byte	0
	.uleb128 0x30
	.long	.LASF95
	.byte	0x1
	.value	0x132
	.long	0x9a
	.quad	.LFB46
	.quad	.LFE46-.LFB46
	.uleb128 0x1
	.byte	0x9c
	.long	0xdd5
	.uleb128 0x31
	.string	"s"
	.byte	0x1
	.value	0x134
	.long	0x9a
	.long	.LLST51
	.uleb128 0x32
	.long	0x3cb
	.quad	.LBB53
	.quad	.LBE53-.LBB53
	.byte	0x1
	.value	0x13b
	.long	0xd88
	.uleb128 0x1f
	.long	0x3f1
	.long	.LLST52
	.uleb128 0x1f
	.long	0x3e6
	.long	.LLST53
	.uleb128 0x1f
	.long	0x3db
	.long	.LLST54
	.uleb128 0x21
	.quad	.LVL168
	.long	0x19da
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	buf
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x3
	.byte	0xa
	.value	0x400
	.byte	0
	.byte	0
	.uleb128 0x23
	.quad	.LVL166
	.long	0x19e9
	.long	0xdac
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x30
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC3
	.byte	0
	.uleb128 0x21
	.quad	.LVL169
	.long	0x19e9
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	buf
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC3
	.byte	0
	.byte	0
	.uleb128 0x33
	.long	.LASF96
	.byte	0x1
	.value	0x149
	.long	0x9a
	.quad	.LFB47
	.quad	.LFE47-.LFB47
	.uleb128 0x1
	.byte	0x9c
	.long	0xfe6
	.uleb128 0x2b
	.long	.LASF97
	.byte	0x1
	.value	0x149
	.long	0x62
	.long	.LLST55
	.uleb128 0x34
	.long	.LASF98
	.byte	0x1
	.value	0x14c
	.long	0xfe6
	.uleb128 0x9
	.byte	0x3
	.quad	token_buf.3686
	.uleb128 0x34
	.long	.LASF99
	.byte	0x1
	.value	0x14d
	.long	0x62
	.uleb128 0x9
	.byte	0x3
	.quad	first.3687
	.uleb128 0x34
	.long	.LASF100
	.byte	0x1
	.value	0x14e
	.long	0x62
	.uleb128 0x9
	.byte	0x3
	.quad	bindex.3688
	.uleb128 0x34
	.long	.LASF101
	.byte	0x1
	.value	0x14f
	.long	0xffd
	.uleb128 0x9
	.byte	0x3
	.quad	sbuf.3689
	.uleb128 0x2c
	.long	.LASF102
	.byte	0x1
	.value	0x150
	.long	0x9a
	.long	.LLST56
	.uleb128 0x31
	.string	"i"
	.byte	0x1
	.value	0x151
	.long	0x62
	.long	.LLST57
	.uleb128 0x2c
	.long	.LASF93
	.byte	0x1
	.value	0x151
	.long	0x62
	.long	.LLST58
	.uleb128 0x2c
	.long	.LASF53
	.byte	0x1
	.value	0x161
	.long	0x9a
	.long	.LLST59
	.uleb128 0x35
	.long	.Ldebug_ranges0+0
	.long	0xf19
	.uleb128 0x2c
	.long	.LASF53
	.byte	0x1
	.value	0x158
	.long	0x9a
	.long	.LLST60
	.uleb128 0x32
	.long	0x39f
	.quad	.LBB56
	.quad	.LBE56-.LBB56
	.byte	0x1
	.value	0x15c
	.long	0xf0b
	.uleb128 0x1f
	.long	0x3ba
	.long	.LLST61
	.uleb128 0x1f
	.long	0x3af
	.long	.LLST62
	.uleb128 0x21
	.quad	.LVL180
	.long	0x19a8
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x14
	.byte	0x73
	.sleb128 -1
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x3a
	.byte	0x24
	.byte	0x3
	.quad	token_buf.3686
	.byte	0x22
	.byte	0
	.byte	0
	.uleb128 0x27
	.quad	.LVL176
	.long	0xcfe
	.byte	0
	.uleb128 0x32
	.long	0x39f
	.quad	.LBB58
	.quad	.LBE58-.LBB58
	.byte	0x1
	.value	0x165
	.long	0xf6e
	.uleb128 0x1f
	.long	0x3ba
	.long	.LLST63
	.uleb128 0x1f
	.long	0x3af
	.long	.LLST64
	.uleb128 0x21
	.quad	.LVL186
	.long	0x19a8
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x14
	.byte	0x73
	.sleb128 -1
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x3a
	.byte	0x24
	.byte	0x3
	.quad	token_buf.3686
	.byte	0x22
	.byte	0
	.byte	0
	.uleb128 0x32
	.long	0x39f
	.quad	.LBB60
	.quad	.LBE60-.LBB60
	.byte	0x1
	.value	0x170
	.long	0xfb7
	.uleb128 0x1f
	.long	0x3ba
	.long	.LLST65
	.uleb128 0x1f
	.long	0x3af
	.long	.LLST66
	.uleb128 0x21
	.quad	.LVL195
	.long	0x19a8
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7e
	.sleb128 0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.byte	0
	.uleb128 0x27
	.quad	.LVL173
	.long	0xcfe
	.uleb128 0x27
	.quad	.LVL182
	.long	0xcfe
	.uleb128 0x21
	.quad	.LVL196
	.long	0x607
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.byte	0
	.uleb128 0xb
	.long	0xa0
	.long	0xffd
	.uleb128 0xc
	.long	0x86
	.byte	0x9
	.uleb128 0x36
	.long	0x86
	.value	0x3ff
	.byte	0
	.uleb128 0xb
	.long	0xa0
	.long	0x100e
	.uleb128 0x36
	.long	0x86
	.value	0x3ff
	.byte	0
	.uleb128 0x29
	.long	.LASF103
	.byte	0x1
	.value	0x17f
	.quad	.LFB48
	.quad	.LFE48-.LFB48
	.uleb128 0x1
	.byte	0x9c
	.long	0x11d8
	.uleb128 0x2a
	.string	"src"
	.byte	0x1
	.value	0x17f
	.long	0x38f
	.long	.LLST67
	.uleb128 0x2b
	.long	.LASF104
	.byte	0x1
	.value	0x17f
	.long	0x62
	.long	.LLST68
	.uleb128 0x2b
	.long	.LASF97
	.byte	0x1
	.value	0x17f
	.long	0x62
	.long	.LLST69
	.uleb128 0x2b
	.long	.LASF75
	.byte	0x1
	.value	0x17f
	.long	0x62
	.long	.LLST70
	.uleb128 0x2b
	.long	.LASF87
	.byte	0x1
	.value	0x17f
	.long	0x62
	.long	.LLST71
	.uleb128 0x2b
	.long	.LASF90
	.byte	0x1
	.value	0x180
	.long	0x322
	.long	.LLST72
	.uleb128 0x37
	.long	.LASF91
	.byte	0x1
	.value	0x180
	.long	0x2c4
	.uleb128 0x2
	.byte	0x91
	.sleb128 0
	.uleb128 0x37
	.long	.LASF92
	.byte	0x1
	.value	0x181
	.long	0x342
	.uleb128 0x2
	.byte	0x91
	.sleb128 8
	.uleb128 0x31
	.string	"s"
	.byte	0x1
	.value	0x183
	.long	0x9a
	.long	.LLST73
	.uleb128 0x31
	.string	"ls"
	.byte	0x1
	.value	0x184
	.long	0x317
	.long	.LLST74
	.uleb128 0x32
	.long	0x3fd
	.quad	.LBB63
	.quad	.LBE63-.LBB63
	.byte	0x1
	.value	0x18f
	.long	0x110d
	.uleb128 0x1f
	.long	0x40d
	.long	.LLST75
	.uleb128 0x21
	.quad	.LVL215
	.long	0x19f5
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC4
	.byte	0
	.byte	0
	.uleb128 0x32
	.long	0x3fd
	.quad	.LBB65
	.quad	.LBE65-.LBB65
	.byte	0x1
	.value	0x193
	.long	0x1153
	.uleb128 0x1f
	.long	0x40d
	.long	.LLST76
	.uleb128 0x21
	.quad	.LVL219
	.long	0x19f5
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC5
	.byte	0
	.byte	0
	.uleb128 0x23
	.quad	.LVL207
	.long	0xcd2
	.long	0x116c
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x3
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0
	.uleb128 0x23
	.quad	.LVL208
	.long	0x553
	.long	0x1184
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7f
	.sleb128 0
	.byte	0
	.uleb128 0x23
	.quad	.LVL210
	.long	0xc23
	.long	0x11a8
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x7e
	.sleb128 0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x2
	.byte	0x7d
	.sleb128 0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x52
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.byte	0
	.uleb128 0x23
	.quad	.LVL211
	.long	0xdd5
	.long	0x11c0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.byte	0
	.uleb128 0x21
	.quad	.LVL212
	.long	0xb36
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x5
	.byte	0x91
	.sleb128 -68
	.byte	0x94
	.byte	0x4
	.byte	0
	.byte	0
	.uleb128 0x33
	.long	.LASF105
	.byte	0x1
	.value	0x197
	.long	0x62
	.quad	.LFB49
	.quad	.LFE49-.LFB49
	.uleb128 0x1
	.byte	0x9c
	.long	0x1750
	.uleb128 0x2b
	.long	.LASF106
	.byte	0x1
	.value	0x197
	.long	0x62
	.long	.LLST77
	.uleb128 0x2b
	.long	.LASF107
	.byte	0x1
	.value	0x197
	.long	0x1750
	.long	.LLST78
	.uleb128 0x34
	.long	.LASF104
	.byte	0x1
	.value	0x199
	.long	0x62
	.uleb128 0x3
	.byte	0x91
	.sleb128 -76
	.uleb128 0x34
	.long	.LASF75
	.byte	0x1
	.value	0x19a
	.long	0x62
	.uleb128 0x3
	.byte	0x91
	.sleb128 -72
	.uleb128 0x34
	.long	.LASF108
	.byte	0x1
	.value	0x19b
	.long	0x62
	.uleb128 0x3
	.byte	0x91
	.sleb128 -68
	.uleb128 0x34
	.long	.LASF109
	.byte	0x1
	.value	0x19c
	.long	0x62
	.uleb128 0x2
	.byte	0x91
	.sleb128 -64
	.uleb128 0x34
	.long	.LASF110
	.byte	0x1
	.value	0x19d
	.long	0x62
	.uleb128 0x2
	.byte	0x91
	.sleb128 -60
	.uleb128 0x34
	.long	.LASF97
	.byte	0x1
	.value	0x19e
	.long	0x62
	.uleb128 0x2
	.byte	0x91
	.sleb128 -56
	.uleb128 0x34
	.long	.LASF87
	.byte	0x1
	.value	0x19f
	.long	0x62
	.uleb128 0x2
	.byte	0x91
	.sleb128 -52
	.uleb128 0x34
	.long	.LASF111
	.byte	0x1
	.value	0x1a0
	.long	0x9a
	.uleb128 0x2
	.byte	0x91
	.sleb128 -48
	.uleb128 0x2c
	.long	.LASF112
	.byte	0x1
	.value	0x1a1
	.long	0x38f
	.long	.LLST79
	.uleb128 0x32
	.long	0x3fd
	.quad	.LBB67
	.quad	.LBE67-.LBB67
	.byte	0x1
	.value	0x1ac
	.long	0x12eb
	.uleb128 0x1f
	.long	0x40d
	.long	.LLST80
	.uleb128 0x21
	.quad	.LVL237
	.long	0x19f5
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC14
	.byte	0
	.byte	0
	.uleb128 0x32
	.long	0x3fd
	.quad	.LBB69
	.quad	.LBE69-.LBB69
	.byte	0x1
	.value	0x1ad
	.long	0x1331
	.uleb128 0x1f
	.long	0x40d
	.long	.LLST81
	.uleb128 0x21
	.quad	.LVL238
	.long	0x19f5
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC15
	.byte	0
	.byte	0
	.uleb128 0x32
	.long	0x3fd
	.quad	.LBB71
	.quad	.LBE71-.LBB71
	.byte	0x1
	.value	0x1ae
	.long	0x1377
	.uleb128 0x1f
	.long	0x40d
	.long	.LLST82
	.uleb128 0x21
	.quad	.LVL239
	.long	0x19f5
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC16
	.byte	0
	.byte	0
	.uleb128 0x32
	.long	0x3fd
	.quad	.LBB73
	.quad	.LBE73-.LBB73
	.byte	0x1
	.value	0x1af
	.long	0x13bd
	.uleb128 0x1f
	.long	0x40d
	.long	.LLST83
	.uleb128 0x21
	.quad	.LVL240
	.long	0x19f5
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC17
	.byte	0
	.byte	0
	.uleb128 0x32
	.long	0x367
	.quad	.LBB75
	.quad	.LBE75-.LBB75
	.byte	0x1
	.value	0x1b1
	.long	0x1408
	.uleb128 0x1f
	.long	0x382
	.long	.LLST84
	.uleb128 0x20
	.long	0x377
	.uleb128 0x21
	.quad	.LVL242
	.long	0x1a00
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x9
	.byte	0x3
	.quad	.LC18
	.byte	0
	.byte	0
	.uleb128 0x32
	.long	0x367
	.quad	.LBB77
	.quad	.LBE77-.LBB77
	.byte	0x1
	.value	0x1b5
	.long	0x1453
	.uleb128 0x1f
	.long	0x382
	.long	.LLST85
	.uleb128 0x20
	.long	0x377
	.uleb128 0x21
	.quad	.LVL245
	.long	0x1a00
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x9
	.byte	0x3
	.quad	.LC19
	.byte	0
	.byte	0
	.uleb128 0x32
	.long	0x367
	.quad	.LBB79
	.quad	.LBE79-.LBB79
	.byte	0x1
	.value	0x1b9
	.long	0x149e
	.uleb128 0x1f
	.long	0x382
	.long	.LLST86
	.uleb128 0x20
	.long	0x377
	.uleb128 0x21
	.quad	.LVL248
	.long	0x1a00
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x9
	.byte	0x3
	.quad	.LC20
	.byte	0
	.byte	0
	.uleb128 0x32
	.long	0x367
	.quad	.LBB81
	.quad	.LBE81-.LBB81
	.byte	0x1
	.value	0x1bf
	.long	0x14e9
	.uleb128 0x1f
	.long	0x382
	.long	.LLST87
	.uleb128 0x20
	.long	0x377
	.uleb128 0x21
	.quad	.LVL254
	.long	0x1a00
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x9
	.byte	0x3
	.quad	.LC22
	.byte	0
	.byte	0
	.uleb128 0x32
	.long	0x3fd
	.quad	.LBB83
	.quad	.LBE83-.LBB83
	.byte	0x1
	.value	0x1c7
	.long	0x152f
	.uleb128 0x1f
	.long	0x40d
	.long	.LLST88
	.uleb128 0x21
	.quad	.LVL258
	.long	0x19f5
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC24
	.byte	0
	.byte	0
	.uleb128 0x23
	.quad	.LVL227
	.long	0x1a0b
	.long	0x1555
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC6
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x3
	.byte	0x91
	.sleb128 -76
	.byte	0
	.uleb128 0x23
	.quad	.LVL228
	.long	0x1a0b
	.long	0x157b
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC7
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x3
	.byte	0x91
	.sleb128 -72
	.byte	0
	.uleb128 0x23
	.quad	.LVL229
	.long	0x1a0b
	.long	0x15a1
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC8
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x3
	.byte	0x91
	.sleb128 -68
	.byte	0
	.uleb128 0x23
	.quad	.LVL230
	.long	0x1a0b
	.long	0x15c6
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC9
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x91
	.sleb128 -64
	.byte	0
	.uleb128 0x23
	.quad	.LVL231
	.long	0x1a0b
	.long	0x15eb
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC10
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x91
	.sleb128 -60
	.byte	0
	.uleb128 0x23
	.quad	.LVL232
	.long	0x1a0b
	.long	0x1610
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC11
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x91
	.sleb128 -56
	.byte	0
	.uleb128 0x23
	.quad	.LVL233
	.long	0x1a0b
	.long	0x1635
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC12
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x91
	.sleb128 -52
	.byte	0
	.uleb128 0x23
	.quad	.LVL234
	.long	0x1a16
	.long	0x165a
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC13
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x91
	.sleb128 -48
	.byte	0
	.uleb128 0x23
	.quad	.LVL235
	.long	0x1a21
	.long	0x167d
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x76
	.sleb128 0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x1
	.byte	0x30
	.byte	0
	.uleb128 0x27
	.quad	.LVL236
	.long	0x1a2c
	.uleb128 0x23
	.quad	.LVL243
	.long	0x1990
	.long	0x16a1
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.byte	0
	.uleb128 0x23
	.quad	.LVL246
	.long	0x1990
	.long	0x16b8
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.byte	0
	.uleb128 0x23
	.quad	.LVL249
	.long	0x1990
	.long	0x16cf
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.byte	0
	.uleb128 0x23
	.quad	.LVL250
	.long	0x1a37
	.long	0x16ee
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC21
	.byte	0
	.uleb128 0x23
	.quad	.LVL255
	.long	0x1990
	.long	0x1705
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.byte	0
	.uleb128 0x23
	.quad	.LVL256
	.long	0x100e
	.long	0x171d
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.uleb128 0x27
	.quad	.LVL257
	.long	0x1a43
	.uleb128 0x23
	.quad	.LVL259
	.long	0x1a4e
	.long	0x1742
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.uleb128 0x27
	.quad	.LVL261
	.long	0x1a59
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x9a
	.uleb128 0x34
	.long	.LASF113
	.byte	0x1
	.value	0x12f
	.long	0x176d
	.uleb128 0xa
	.byte	0x3
	.quad	.LC3
	.byte	0x9f
	.uleb128 0xd
	.long	0x9a
	.uleb128 0x38
	.long	.LASF114
	.byte	0x6
	.byte	0xa8
	.long	0x26d
	.uleb128 0x38
	.long	.LASF115
	.byte	0x6
	.byte	0xa9
	.long	0x26d
	.uleb128 0x38
	.long	.LASF116
	.byte	0x6
	.byte	0xaa
	.long	0x26d
	.uleb128 0x39
	.long	.LASF117
	.byte	0x1
	.byte	0xe
	.long	0x62
	.uleb128 0x9
	.byte	0x3
	.quad	wcnt
	.uleb128 0x39
	.long	.LASF118
	.byte	0x1
	.byte	0xf
	.long	0x62
	.uleb128 0x9
	.byte	0x3
	.quad	ucnt
	.uleb128 0x39
	.long	.LASF119
	.byte	0x1
	.byte	0x10
	.long	0x62
	.uleb128 0x9
	.byte	0x3
	.quad	mcnt
	.uleb128 0x39
	.long	.LASF120
	.byte	0x1
	.byte	0x11
	.long	0x62
	.uleb128 0x9
	.byte	0x3
	.quad	scnt
	.uleb128 0x39
	.long	.LASF121
	.byte	0x1
	.byte	0x12
	.long	0x9a
	.uleb128 0x9
	.byte	0x3
	.quad	mstring
	.uleb128 0x39
	.long	.LASF122
	.byte	0x1
	.byte	0x13
	.long	0x62
	.uleb128 0x9
	.byte	0x3
	.quad	llen
	.uleb128 0x39
	.long	.LASF123
	.byte	0x1
	.byte	0x14
	.long	0x9a
	.uleb128 0x9
	.byte	0x3
	.quad	lstring
	.uleb128 0x39
	.long	.LASF124
	.byte	0x1
	.byte	0x15
	.long	0x62
	.uleb128 0x9
	.byte	0x3
	.quad	lcnt
	.uleb128 0xb
	.long	0x2c4
	.long	0x184b
	.uleb128 0xc
	.long	0x86
	.byte	0x1
	.byte	0
	.uleb128 0x39
	.long	.LASF125
	.byte	0x1
	.byte	0x33
	.long	0x183b
	.uleb128 0x9
	.byte	0x3
	.quad	lower_fun_set
	.uleb128 0xb
	.long	0x9a
	.long	0x1870
	.uleb128 0xc
	.long	0x86
	.byte	0x1
	.byte	0
	.uleb128 0x39
	.long	.LASF126
	.byte	0x1
	.byte	0x34
	.long	0x1860
	.uleb128 0x9
	.byte	0x3
	.quad	lower_fun_names
	.uleb128 0x39
	.long	.LASF127
	.byte	0x1
	.byte	0x4f
	.long	0xc1d
	.uleb128 0x9
	.byte	0x3
	.quad	htable
	.uleb128 0x39
	.long	.LASF128
	.byte	0x1
	.byte	0x50
	.long	0x62
	.uleb128 0x9
	.byte	0x3
	.quad	tsize
	.uleb128 0xb
	.long	0x322
	.long	0x18bf
	.uleb128 0xc
	.long	0x86
	.byte	0x2
	.byte	0
	.uleb128 0x39
	.long	.LASF129
	.byte	0x1
	.byte	0x94
	.long	0x18af
	.uleb128 0x9
	.byte	0x3
	.quad	hash_fun_set
	.uleb128 0xb
	.long	0x9a
	.long	0x18e4
	.uleb128 0xc
	.long	0x86
	.byte	0x2
	.byte	0
	.uleb128 0x39
	.long	.LASF130
	.byte	0x1
	.byte	0x95
	.long	0x18d4
	.uleb128 0x9
	.byte	0x3
	.quad	hash_fun_names
	.uleb128 0xb
	.long	0x342
	.long	0x1909
	.uleb128 0xc
	.long	0x86
	.byte	0x2
	.byte	0
	.uleb128 0x39
	.long	.LASF131
	.byte	0x1
	.byte	0xea
	.long	0x18f9
	.uleb128 0x9
	.byte	0x3
	.quad	find_ele_fun_set
	.uleb128 0x39
	.long	.LASF132
	.byte	0x1
	.byte	0xec
	.long	0x18d4
	.uleb128 0x9
	.byte	0x3
	.quad	find_ele_fun_names
	.uleb128 0x3a
	.string	"buf"
	.byte	0x1
	.value	0x125
	.long	0xffd
	.uleb128 0x9
	.byte	0x3
	.quad	buf
	.uleb128 0x3b
	.long	.LASF133
	.byte	0x1
	.value	0x126
	.long	0x62
	.uleb128 0x9
	.byte	0x3
	.quad	bufvalid
	.uleb128 0x3b
	.long	.LASF112
	.byte	0x1
	.value	0x127
	.long	0x38f
	.uleb128 0x9
	.byte	0x3
	.quad	infile
	.uleb128 0x3c
	.long	.LASF137
	.long	.LASF138
	.byte	0xa
	.byte	0
	.long	.LASF137
	.uleb128 0x3d
	.long	.LASF134
	.long	.LASF134
	.byte	0x9
	.value	0x1d4
	.uleb128 0x3d
	.long	.LASF135
	.long	.LASF135
	.byte	0x9
	.value	0x21f
	.uleb128 0x3d
	.long	.LASF136
	.long	.LASF136
	.byte	0x9
	.value	0x1d2
	.uleb128 0x3c
	.long	.LASF62
	.long	.LASF139
	.byte	0xa
	.byte	0
	.long	.LASF62
	.uleb128 0x3e
	.long	.LASF140
	.long	.LASF140
	.byte	0xb
	.byte	0x8c
	.uleb128 0x3d
	.long	.LASF141
	.long	.LASF141
	.byte	0x9
	.value	0x2fc
	.uleb128 0x3d
	.long	.LASF142
	.long	.LASF142
	.byte	0x9
	.value	0x1e3
	.uleb128 0x3c
	.long	.LASF65
	.long	.LASF143
	.byte	0x2
	.byte	0xf3
	.long	.LASF65
	.uleb128 0x3d
	.long	.LASF144
	.long	.LASF144
	.byte	0xb
	.value	0x157
	.uleb128 0x3e
	.long	.LASF145
	.long	.LASF145
	.byte	0x2
	.byte	0x57
	.uleb128 0x3e
	.long	.LASF146
	.long	.LASF146
	.byte	0x2
	.byte	0x55
	.uleb128 0x3e
	.long	.LASF147
	.long	.LASF147
	.byte	0xc
	.byte	0x9
	.uleb128 0x3e
	.long	.LASF148
	.long	.LASF148
	.byte	0xc
	.byte	0xb
	.uleb128 0x3e
	.long	.LASF149
	.long	.LASF149
	.byte	0xc
	.byte	0x15
	.uleb128 0x3e
	.long	.LASF150
	.long	.LASF150
	.byte	0xc
	.byte	0x1b
	.uleb128 0x3d
	.long	.LASF151
	.long	.LASF151
	.byte	0x6
	.value	0x110
	.uleb128 0x3e
	.long	.LASF152
	.long	.LASF152
	.byte	0x8
	.byte	0xbd
	.uleb128 0x3e
	.long	.LASF153
	.long	.LASF153
	.byte	0x6
	.byte	0xed
	.uleb128 0x3f
	.long	.LASF160
	.long	.LASF160
	.byte	0
	.section	.debug_abbrev,"",@progbits
.Ldebug_abbrev0:
	.uleb128 0x1
	.uleb128 0x11
	.byte	0x1
	.uleb128 0x25
	.uleb128 0xe
	.uleb128 0x13
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x1b
	.uleb128 0xe
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x10
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x2
	.uleb128 0x16
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x3
	.uleb128 0x24
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3e
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0xe
	.byte	0
	.byte	0
	.uleb128 0x4
	.uleb128 0x24
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3e
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0x8
	.byte	0
	.byte	0
	.uleb128 0x5
	.uleb128 0xf
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x6
	.uleb128 0xf
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x7
	.uleb128 0x13
	.byte	0x1
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x8
	.uleb128 0xd
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x38
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x9
	.uleb128 0xd
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x38
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0xa
	.uleb128 0x16
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0xb
	.uleb128 0x1
	.byte	0x1
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xc
	.uleb128 0x21
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2f
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0xd
	.uleb128 0x26
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xe
	.uleb128 0x26
	.byte	0
	.byte	0
	.byte	0
	.uleb128 0xf
	.uleb128 0x15
	.byte	0x1
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x10
	.uleb128 0x5
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x11
	.uleb128 0x15
	.byte	0x1
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x12
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x20
	.uleb128 0xb
	.uleb128 0x34
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x13
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x14
	.uleb128 0x18
	.byte	0
	.byte	0
	.byte	0
	.uleb128 0x15
	.uleb128 0x37
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x16
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x17
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x18
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x19
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x1a
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x1b
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x1c
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1d
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x1e
	.uleb128 0x1d
	.byte	0x1
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x58
	.uleb128 0xb
	.uleb128 0x59
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1f
	.uleb128 0x5
	.byte	0
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x20
	.uleb128 0x5
	.byte	0
	.uleb128 0x31
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x21
	.uleb128 0x4109
	.byte	0x1
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x31
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x22
	.uleb128 0x410a
	.byte	0
	.uleb128 0x2
	.uleb128 0x18
	.uleb128 0x2111
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x23
	.uleb128 0x4109
	.byte	0x1
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x24
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x25
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x26
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x27
	.uleb128 0x4109
	.byte	0
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x31
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x28
	.uleb128 0xb
	.byte	0x1
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x29
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x2a
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x2b
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x2c
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x2d
	.uleb128 0x4109
	.byte	0x1
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x2113
	.uleb128 0x18
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x2e
	.uleb128 0x4109
	.byte	0x1
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x2113
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x2f
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x30
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x31
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x32
	.uleb128 0x1d
	.byte	0x1
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x58
	.uleb128 0xb
	.uleb128 0x59
	.uleb128 0x5
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x33
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x34
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x35
	.uleb128 0xb
	.byte	0x1
	.uleb128 0x55
	.uleb128 0x17
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x36
	.uleb128 0x21
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2f
	.uleb128 0x5
	.byte	0
	.byte	0
	.uleb128 0x37
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x38
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3c
	.uleb128 0x19
	.byte	0
	.byte	0
	.uleb128 0x39
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x3a
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x3b
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x3c
	.uleb128 0x2e
	.byte	0
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x6e
	.uleb128 0xe
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x6e
	.uleb128 0xe
	.byte	0
	.byte	0
	.uleb128 0x3d
	.uleb128 0x2e
	.byte	0
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x6e
	.uleb128 0xe
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.byte	0
	.byte	0
	.uleb128 0x3e
	.uleb128 0x2e
	.byte	0
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x6e
	.uleb128 0xe
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x3f
	.uleb128 0x2e
	.byte	0
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x6e
	.uleb128 0xe
	.uleb128 0x3
	.uleb128 0xe
	.byte	0
	.byte	0
	.byte	0
	.section	.debug_loc,"",@progbits
.Ldebug_loc0:
.LLST0:
	.quad	.LVL0-.Ltext0
	.quad	.LVL1-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL1-.Ltext0
	.quad	.LFE35-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	0
	.quad	0
.LLST1:
	.quad	.LVL0-.Ltext0
	.quad	.LVL3-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL3-.Ltext0
	.quad	.LVL5-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	.LVL6-.Ltext0
	.quad	.LVL9-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	.LVL9-.Ltext0
	.quad	.LVL10-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST2:
	.quad	.LVL2-.Ltext0
	.quad	.LVL4-.Ltext0
	.value	0x9
	.byte	0x72
	.sleb128 0
	.byte	0x8
	.byte	0x38
	.byte	0x24
	.byte	0x8
	.byte	0x38
	.byte	0x26
	.byte	0x9f
	.quad	.LVL4-.Ltext0
	.quad	.LVL8-.Ltext0
	.value	0x1
	.byte	0x52
	.quad	.LVL8-.Ltext0
	.quad	.LVL10-.Ltext0
	.value	0x9
	.byte	0x72
	.sleb128 0
	.byte	0x8
	.byte	0x38
	.byte	0x24
	.byte	0x8
	.byte	0x38
	.byte	0x26
	.byte	0x9f
	.quad	0
	.quad	0
.LLST3:
	.quad	.LVL11-.Ltext0
	.quad	.LVL12-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL12-.Ltext0
	.quad	.LFE36-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	0
	.quad	0
.LLST4:
	.quad	.LVL11-.Ltext0
	.quad	.LVL14-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL14-.Ltext0
	.quad	.LVL19-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL19-.Ltext0
	.quad	.LVL20-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST5:
	.quad	.LVL13-.Ltext0
	.quad	.LVL15-.Ltext0
	.value	0x9
	.byte	0x72
	.sleb128 0
	.byte	0x8
	.byte	0x38
	.byte	0x24
	.byte	0x8
	.byte	0x38
	.byte	0x26
	.byte	0x9f
	.quad	.LVL15-.Ltext0
	.quad	.LVL18-.Ltext0
	.value	0x1
	.byte	0x52
	.quad	.LVL18-.Ltext0
	.quad	.LVL20-.Ltext0
	.value	0x9
	.byte	0x72
	.sleb128 0
	.byte	0x8
	.byte	0x38
	.byte	0x24
	.byte	0x8
	.byte	0x38
	.byte	0x26
	.byte	0x9f
	.quad	0
	.quad	0
.LLST6:
	.quad	.LVL21-.Ltext0
	.quad	.LVL22-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL22-.Ltext0
	.quad	.LVL33-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	0
	.quad	0
.LLST7:
	.quad	.LVL21-.Ltext0
	.quad	.LVL24-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL24-.Ltext0
	.quad	.LVL27-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL28-.Ltext0
	.quad	.LVL31-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL31-.Ltext0
	.quad	.LVL32-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST8:
	.quad	.LVL23-.Ltext0
	.quad	.LVL25-.Ltext0
	.value	0x9
	.byte	0x72
	.sleb128 0
	.byte	0x8
	.byte	0x38
	.byte	0x24
	.byte	0x8
	.byte	0x38
	.byte	0x26
	.byte	0x9f
	.quad	.LVL25-.Ltext0
	.quad	.LVL26-.Ltext0
	.value	0x1
	.byte	0x52
	.quad	.LVL30-.Ltext0
	.quad	.LVL32-.Ltext0
	.value	0x9
	.byte	0x72
	.sleb128 0
	.byte	0x8
	.byte	0x38
	.byte	0x24
	.byte	0x8
	.byte	0x38
	.byte	0x26
	.byte	0x9f
	.quad	0
	.quad	0
.LLST9:
	.quad	.LVL35-.Ltext0
	.quad	.LVL36-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL36-.Ltext0
	.quad	.LVL37-1-.Ltext0
	.value	0x9
	.byte	0x3
	.quad	tsize
	.quad	.LVL37-1-.Ltext0
	.quad	.LFE33-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST10:
	.quad	.LVL38-.Ltext0
	.quad	.LVL39-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC0
	.byte	0x9f
	.quad	0
	.quad	0
.LLST11:
	.quad	.LVL41-.Ltext0
	.quad	.LVL42-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	0
	.quad	0
.LLST12:
	.quad	.LVL43-.Ltext0
	.quad	.LVL45-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL45-.Ltext0
	.quad	.LVL49-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL49-.Ltext0
	.quad	.LFE30-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST13:
	.quad	.LVL44-.Ltext0
	.quad	.LVL45-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL45-.Ltext0
	.quad	.LVL48-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	0
	.quad	0
.LLST14:
	.quad	.LVL50-.Ltext0
	.quad	.LVL51-1-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL51-1-.Ltext0
	.quad	.LVL56-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL56-.Ltext0
	.quad	.LFE31-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST15:
	.quad	.LVL51-.Ltext0
	.quad	.LVL53-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL53-.Ltext0
	.quad	.LVL54-.Ltext0
	.value	0x6
	.byte	0x71
	.sleb128 0
	.byte	0x73
	.sleb128 0
	.byte	0x1c
	.byte	0x9f
	.quad	.LVL54-.Ltext0
	.quad	.LVL55-.Ltext0
	.value	0x8
	.byte	0x71
	.sleb128 0
	.byte	0x73
	.sleb128 0
	.byte	0x1c
	.byte	0x23
	.uleb128 0x1
	.byte	0x9f
	.quad	0
	.quad	0
.LLST16:
	.quad	.LVL51-.Ltext0
	.quad	.LVL52-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST17:
	.quad	.LVL57-.Ltext0
	.quad	.LVL58-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL58-.Ltext0
	.quad	.LVL67-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL67-.Ltext0
	.quad	.LFE34-.Ltext0
	.value	0x2
	.byte	0x70
	.sleb128 0
	.quad	0
	.quad	0
.LLST18:
	.quad	.LVL60-.Ltext0
	.quad	.LVL61-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL61-1-.Ltext0
	.quad	.LVL66-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL66-.Ltext0
	.quad	.LFE34-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST19:
	.quad	.LVL61-.Ltext0
	.quad	.LVL63-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL64-.Ltext0
	.quad	.LVL65-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST20:
	.quad	.LVL62-.Ltext0
	.quad	.LVL63-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC1
	.byte	0x9f
	.quad	0
	.quad	0
.LLST21:
	.quad	.LVL68-.Ltext0
	.quad	.LVL69-1-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL69-1-.Ltext0
	.quad	.LVL77-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL77-.Ltext0
	.quad	.LFE38-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST22:
	.quad	.LVL70-.Ltext0
	.quad	.LVL72-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL73-.Ltext0
	.quad	.LVL75-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL75-1-.Ltext0
	.quad	.LVL76-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL76-.Ltext0
	.quad	.LFE38-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST23:
	.quad	.LVL71-.Ltext0
	.quad	.LVL72-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC2
	.byte	0x9f
	.quad	0
	.quad	0
.LLST24:
	.quad	.LVL74-.Ltext0
	.quad	.LVL75-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	0
	.quad	0
.LLST25:
	.quad	.LVL74-.Ltext0
	.quad	.LVL75-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	0
	.quad	0
.LLST26:
	.quad	.LVL78-.Ltext0
	.quad	.LVL79-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL79-.Ltext0
	.quad	.LVL82-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	.LVL82-.Ltext0
	.quad	.LVL84-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL84-.Ltext0
	.quad	.LVL86-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL86-.Ltext0
	.quad	.LVL87-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL87-.Ltext0
	.quad	.LVL89-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL89-.Ltext0
	.quad	.LFE39-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST27:
	.quad	.LVL78-.Ltext0
	.quad	.LVL80-1-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	.LVL80-1-.Ltext0
	.quad	.LVL82-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL82-.Ltext0
	.quad	.LVL83-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	.LVL83-.Ltext0
	.quad	.LVL90-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL90-.Ltext0
	.quad	.LFE39-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x54
	.byte	0x9f
	.quad	0
	.quad	0
.LLST28:
	.quad	.LVL91-.Ltext0
	.quad	.LVL93-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL93-.Ltext0
	.quad	.LVL97-.Ltext0
	.value	0x1
	.byte	0x5d
	.quad	.LVL97-.Ltext0
	.quad	.LVL98-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL98-.Ltext0
	.quad	.LVL105-.Ltext0
	.value	0x1
	.byte	0x5d
	.quad	.LVL105-.Ltext0
	.quad	.LFE40-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST29:
	.quad	.LVL91-.Ltext0
	.quad	.LVL93-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	.LVL93-.Ltext0
	.quad	.LVL104-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL104-.Ltext0
	.quad	.LFE40-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x54
	.byte	0x9f
	.quad	0
	.quad	0
.LLST30:
	.quad	.LVL92-.Ltext0
	.quad	.LVL93-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL93-.Ltext0
	.quad	.LVL100-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL102-.Ltext0
	.quad	.LVL103-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST31:
	.quad	.LVL94-.Ltext0
	.quad	.LVL100-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	0
	.quad	0
.LLST32:
	.quad	.LVL96-.Ltext0
	.quad	.LVL98-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	0
	.quad	0
.LLST33:
	.quad	.LVL106-.Ltext0
	.quad	.LVL108-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL108-.Ltext0
	.quad	.LVL113-.Ltext0
	.value	0x1
	.byte	0x5d
	.quad	.LVL113-.Ltext0
	.quad	.LVL114-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL114-.Ltext0
	.quad	.LVL119-.Ltext0
	.value	0x1
	.byte	0x5d
	.quad	.LVL119-.Ltext0
	.quad	.LVL120-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL120-.Ltext0
	.quad	.LFE41-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST34:
	.quad	.LVL106-.Ltext0
	.quad	.LVL108-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	.LVL108-.Ltext0
	.quad	.LVL119-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL119-.Ltext0
	.quad	.LVL121-1-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	.LVL121-1-.Ltext0
	.quad	.LVL124-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL124-.Ltext0
	.quad	.LFE41-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x54
	.byte	0x9f
	.quad	0
	.quad	0
.LLST35:
	.quad	.LVL107-.Ltext0
	.quad	.LVL108-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL108-.Ltext0
	.quad	.LVL109-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL109-.Ltext0
	.quad	.LVL115-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL115-.Ltext0
	.quad	.LVL116-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL117-.Ltext0
	.quad	.LVL118-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL118-.Ltext0
	.quad	.LVL119-.Ltext0
	.value	0x2
	.byte	0x73
	.sleb128 16
	.quad	.LVL119-.Ltext0
	.quad	.LVL120-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL120-.Ltext0
	.quad	.LVL122-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	.LVL122-.Ltext0
	.quad	.LVL123-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST36:
	.quad	.LVL107-.Ltext0
	.quad	.LVL108-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL108-.Ltext0
	.quad	.LVL109-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL109-.Ltext0
	.quad	.LVL114-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL114-.Ltext0
	.quad	.LVL119-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL119-.Ltext0
	.quad	.LVL123-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST37:
	.quad	.LVL108-.Ltext0
	.quad	.LVL109-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL110-.Ltext0
	.quad	.LVL119-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	0
	.quad	0
.LLST38:
	.quad	.LVL112-.Ltext0
	.quad	.LVL114-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	0
	.quad	0
.LLST39:
	.quad	.LVL125-.Ltext0
	.quad	.LVL127-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL127-.Ltext0
	.quad	.LVL149-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL149-.Ltext0
	.quad	.LVL151-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	.LVL151-.Ltext0
	.quad	.LVL152-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL152-.Ltext0
	.quad	.LVL153-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	.LVL153-.Ltext0
	.quad	.LVL154-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL154-.Ltext0
	.quad	.LFE43-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST40:
	.quad	.LVL126-.Ltext0
	.quad	.LVL149-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL149-.Ltext0
	.quad	.LVL151-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL151-.Ltext0
	.quad	.LVL152-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL152-.Ltext0
	.quad	.LVL153-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL153-.Ltext0
	.quad	.LVL154-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL154-.Ltext0
	.quad	.LVL157-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL157-.Ltext0
	.quad	.LFE43-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST41:
	.quad	.LVL132-.Ltext0
	.quad	.LVL146-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	0
	.quad	0
.LLST42:
	.quad	.LVL129-.Ltext0
	.quad	.LVL131-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL131-.Ltext0
	.quad	.LVL146-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL146-.Ltext0
	.quad	.LVL147-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL147-.Ltext0
	.quad	.LVL151-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL151-.Ltext0
	.quad	.LVL154-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL154-.Ltext0
	.quad	.LVL156-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	0
	.quad	0
.LLST43:
	.quad	.LVL130-.Ltext0
	.quad	.LVL131-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL131-.Ltext0
	.quad	.LVL144-.Ltext0
	.value	0x1
	.byte	0x59
	.quad	.LVL144-.Ltext0
	.quad	.LVL145-.Ltext0
	.value	0x3
	.byte	0x79
	.sleb128 1
	.byte	0x9f
	.quad	.LVL146-.Ltext0
	.quad	.LVL147-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL151-.Ltext0
	.quad	.LVL154-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST44:
	.quad	.LVL133-.Ltext0
	.quad	.LVL134-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL134-.Ltext0
	.quad	.LVL135-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	.LVL136-.Ltext0
	.quad	.LVL137-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	.LVL137-.Ltext0
	.quad	.LVL138-.Ltext0
	.value	0x3
	.byte	0x71
	.sleb128 -1
	.byte	0x9f
	.quad	.LVL138-.Ltext0
	.quad	.LVL139-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	.LVL139-.Ltext0
	.quad	.LVL140-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL140-.Ltext0
	.quad	.LVL141-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	.LVL149-.Ltext0
	.quad	.LVL150-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL152-.Ltext0
	.quad	.LVL153-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST45:
	.quad	.LVL129-.Ltext0
	.quad	.LVL131-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL131-.Ltext0
	.quad	.LVL134-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL134-.Ltext0
	.quad	.LVL135-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	.LVL135-.Ltext0
	.quad	.LVL146-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL146-.Ltext0
	.quad	.LVL147-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL151-.Ltext0
	.quad	.LVL154-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST46:
	.quad	.LVL158-.Ltext0
	.quad	.LVL159-1-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL159-1-.Ltext0
	.quad	.LVL163-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL163-.Ltext0
	.quad	.LFE44-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST47:
	.quad	.LVL158-.Ltext0
	.quad	.LVL159-1-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	.LVL159-1-.Ltext0
	.quad	.LVL161-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL161-.Ltext0
	.quad	.LFE44-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x54
	.byte	0x9f
	.quad	0
	.quad	0
.LLST48:
	.quad	.LVL158-.Ltext0
	.quad	.LVL159-1-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	.LVL159-1-.Ltext0
	.quad	.LFE44-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x51
	.byte	0x9f
	.quad	0
	.quad	0
.LLST49:
	.quad	.LVL158-.Ltext0
	.quad	.LVL159-1-.Ltext0
	.value	0x1
	.byte	0x52
	.quad	.LVL159-1-.Ltext0
	.quad	.LVL164-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL164-.Ltext0
	.quad	.LFE44-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x52
	.byte	0x9f
	.quad	0
	.quad	0
.LLST50:
	.quad	.LVL160-.Ltext0
	.quad	.LVL162-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST51:
	.quad	.LVL166-.Ltext0
	.quad	.LVL167-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL169-.Ltext0
	.quad	.LVL170-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST52:
	.quad	.LVL167-.Ltext0
	.quad	.LVL168-1-.Ltext0
	.value	0x9
	.byte	0x3
	.quad	infile
	.quad	0
	.quad	0
.LLST53:
	.quad	.LVL167-.Ltext0
	.quad	.LVL168-.Ltext0
	.value	0x4
	.byte	0xa
	.value	0x400
	.byte	0x9f
	.quad	0
	.quad	0
.LLST54:
	.quad	.LVL167-.Ltext0
	.quad	.LVL168-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	buf
	.byte	0x9f
	.quad	0
	.quad	0
.LLST55:
	.quad	.LVL171-.Ltext0
	.quad	.LVL173-1-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL173-1-.Ltext0
	.quad	.LVL174-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	.LVL174-.Ltext0
	.quad	.LVL175-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL175-.Ltext0
	.quad	.LVL205-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL205-.Ltext0
	.quad	.LFE47-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST56:
	.quad	.LVL172-.Ltext0
	.quad	.LVL190-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	sbuf.3689
	.byte	0x9f
	.quad	.LVL190-.Ltext0
	.quad	.LVL191-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL191-.Ltext0
	.quad	.LVL192-.Ltext0
	.value	0x1
	.byte	0x5e
	.quad	.LVL192-.Ltext0
	.quad	.LVL193-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL193-.Ltext0
	.quad	.LVL197-.Ltext0
	.value	0x1
	.byte	0x5e
	.quad	.LVL197-.Ltext0
	.quad	.LVL201-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL202-.Ltext0
	.quad	.LVL205-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	sbuf.3689
	.byte	0x9f
	.quad	0
	.quad	0
.LLST57:
	.quad	.LVL189-.Ltext0
	.quad	.LVL190-.Ltext0
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL190-.Ltext0
	.quad	.LVL193-.Ltext0
	.value	0x1
	.byte	0x5d
	.quad	.LVL200-.Ltext0
	.quad	.LVL202-.Ltext0
	.value	0x1
	.byte	0x5d
	.quad	0
	.quad	0
.LLST58:
	.quad	.LVL187-.Ltext0
	.quad	.LVL188-.Ltext0
	.value	0xf
	.byte	0x3
	.quad	bindex.3688
	.byte	0x94
	.byte	0x4
	.byte	0x76
	.sleb128 0
	.byte	0x1c
	.byte	0x9f
	.quad	.LVL188-.Ltext0
	.quad	.LVL202-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	0
	.quad	0
.LLST59:
	.quad	.LVL182-.Ltext0
	.quad	.LVL186-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL190-.Ltext0
	.quad	.LVL193-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL194-.Ltext0
	.quad	.LVL202-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL204-.Ltext0
	.quad	.LVL205-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST60:
	.quad	.LVL176-.Ltext0
	.quad	.LVL180-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL202-.Ltext0
	.quad	.LVL203-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST61:
	.quad	.LVL177-.Ltext0
	.quad	.LVL180-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST62:
	.quad	.LVL177-.Ltext0
	.quad	.LVL178-.Ltext0
	.value	0x15
	.byte	0x75
	.sleb128 0
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x3a
	.byte	0x24
	.byte	0x3
	.quad	token_buf.3686
	.byte	0x22
	.byte	0x9f
	.quad	.LVL178-.Ltext0
	.quad	.LVL179-.Ltext0
	.value	0x15
	.byte	0x73
	.sleb128 -1
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x3a
	.byte	0x24
	.byte	0x3
	.quad	token_buf.3686
	.byte	0x22
	.byte	0x9f
	.quad	.LVL179-.Ltext0
	.quad	.LVL180-1-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL180-1-.Ltext0
	.quad	.LVL181-.Ltext0
	.value	0x15
	.byte	0x73
	.sleb128 -1
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x3a
	.byte	0x24
	.byte	0x3
	.quad	token_buf.3686
	.byte	0x22
	.byte	0x9f
	.quad	0
	.quad	0
.LLST63:
	.quad	.LVL183-.Ltext0
	.quad	.LVL186-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST64:
	.quad	.LVL183-.Ltext0
	.quad	.LVL184-.Ltext0
	.value	0x15
	.byte	0x71
	.sleb128 0
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x3a
	.byte	0x24
	.byte	0x3
	.quad	token_buf.3686
	.byte	0x22
	.byte	0x9f
	.quad	.LVL184-.Ltext0
	.quad	.LVL185-.Ltext0
	.value	0x15
	.byte	0x75
	.sleb128 0
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x3a
	.byte	0x24
	.byte	0x3
	.quad	token_buf.3686
	.byte	0x22
	.byte	0x9f
	.quad	.LVL185-.Ltext0
	.quad	.LVL186-1-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL186-1-.Ltext0
	.quad	.LVL186-.Ltext0
	.value	0x15
	.byte	0x73
	.sleb128 -1
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x3a
	.byte	0x24
	.byte	0x3
	.quad	token_buf.3686
	.byte	0x22
	.byte	0x9f
	.quad	0
	.quad	0
.LLST65:
	.quad	.LVL194-.Ltext0
	.quad	.LVL195-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	0
	.quad	0
.LLST66:
	.quad	.LVL194-.Ltext0
	.quad	.LVL195-.Ltext0
	.value	0x1
	.byte	0x5e
	.quad	0
	.quad	0
.LLST67:
	.quad	.LVL206-.Ltext0
	.quad	.LVL207-1-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL207-1-.Ltext0
	.quad	.LFE48-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST68:
	.quad	.LVL206-.Ltext0
	.quad	.LVL207-1-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	.LVL207-1-.Ltext0
	.quad	.LVL220-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	0
	.quad	0
.LLST69:
	.quad	.LVL206-.Ltext0
	.quad	.LVL207-1-.Ltext0
	.value	0x1
	.byte	0x51
	.quad	.LVL207-1-.Ltext0
	.quad	.LVL213-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL213-.Ltext0
	.quad	.LFE48-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x51
	.byte	0x9f
	.quad	0
	.quad	0
.LLST70:
	.quad	.LVL206-.Ltext0
	.quad	.LVL207-1-.Ltext0
	.value	0x1
	.byte	0x52
	.quad	.LVL207-1-.Ltext0
	.quad	.LVL222-.Ltext0
	.value	0x1
	.byte	0x5f
	.quad	.LVL222-.Ltext0
	.quad	.LFE48-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x52
	.byte	0x9f
	.quad	0
	.quad	0
.LLST71:
	.quad	.LVL206-.Ltext0
	.quad	.LVL207-1-.Ltext0
	.value	0x1
	.byte	0x58
	.quad	.LVL207-1-.Ltext0
	.quad	.LFE48-.Ltext0
	.value	0x3
	.byte	0x91
	.sleb128 -68
	.quad	0
	.quad	0
.LLST72:
	.quad	.LVL206-.Ltext0
	.quad	.LVL207-1-.Ltext0
	.value	0x1
	.byte	0x59
	.quad	.LVL207-1-.Ltext0
	.quad	.LVL221-.Ltext0
	.value	0x1
	.byte	0x5e
	.quad	.LVL221-.Ltext0
	.quad	.LFE48-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x59
	.byte	0x9f
	.quad	0
	.quad	0
.LLST73:
	.quad	.LVL209-.Ltext0
	.quad	.LVL210-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL211-.Ltext0
	.quad	.LVL212-1-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST74:
	.quad	.LVL213-.Ltext0
	.quad	.LVL214-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL214-.Ltext0
	.quad	.LVL218-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	0
	.quad	0
.LLST75:
	.quad	.LVL214-.Ltext0
	.quad	.LVL215-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC4
	.byte	0x9f
	.quad	0
	.quad	0
.LLST76:
	.quad	.LVL218-.Ltext0
	.quad	.LVL219-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC5
	.byte	0x9f
	.quad	0
	.quad	0
.LLST77:
	.quad	.LVL223-.Ltext0
	.quad	.LVL226-.Ltext0
	.value	0x1
	.byte	0x55
	.quad	.LVL226-.Ltext0
	.quad	.LVL262-.Ltext0
	.value	0x1
	.byte	0x56
	.quad	.LVL262-.Ltext0
	.quad	.LFE49-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST78:
	.quad	.LVL223-.Ltext0
	.quad	.LVL225-.Ltext0
	.value	0x1
	.byte	0x54
	.quad	.LVL225-.Ltext0
	.quad	.LVL263-.Ltext0
	.value	0x1
	.byte	0x5c
	.quad	.LVL263-.Ltext0
	.quad	.LFE49-.Ltext0
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x54
	.byte	0x9f
	.quad	0
	.quad	0
.LLST79:
	.quad	.LVL224-.Ltext0
	.quad	.LVL251-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	.LVL251-.Ltext0
	.quad	.LVL253-.Ltext0
	.value	0x1
	.byte	0x50
	.quad	.LVL253-.Ltext0
	.quad	.LVL260-.Ltext0
	.value	0x1
	.byte	0x53
	.quad	0
	.quad	0
.LLST80:
	.quad	.LVL236-.Ltext0
	.quad	.LVL237-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC14
	.byte	0x9f
	.quad	0
	.quad	0
.LLST81:
	.quad	.LVL237-.Ltext0
	.quad	.LVL238-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC15
	.byte	0x9f
	.quad	0
	.quad	0
.LLST82:
	.quad	.LVL238-.Ltext0
	.quad	.LVL239-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC16
	.byte	0x9f
	.quad	0
	.quad	0
.LLST83:
	.quad	.LVL239-.Ltext0
	.quad	.LVL240-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC17
	.byte	0x9f
	.quad	0
	.quad	0
.LLST84:
	.quad	.LVL241-.Ltext0
	.quad	.LVL242-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC18
	.byte	0x9f
	.quad	0
	.quad	0
.LLST85:
	.quad	.LVL244-.Ltext0
	.quad	.LVL245-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC19
	.byte	0x9f
	.quad	0
	.quad	0
.LLST86:
	.quad	.LVL247-.Ltext0
	.quad	.LVL248-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC20
	.byte	0x9f
	.quad	0
	.quad	0
.LLST87:
	.quad	.LVL252-.Ltext0
	.quad	.LVL254-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC22
	.byte	0x9f
	.quad	0
	.quad	0
.LLST88:
	.quad	.LVL257-.Ltext0
	.quad	.LVL258-.Ltext0
	.value	0xa
	.byte	0x3
	.quad	.LC24
	.byte	0x9f
	.quad	0
	.quad	0
	.section	.debug_aranges,"",@progbits
	.long	0x2c
	.value	0x2
	.long	.Ldebug_info0
	.byte	0x8
	.byte	0
	.value	0
	.value	0
	.quad	.Ltext0
	.quad	.Letext0-.Ltext0
	.quad	0
	.quad	0
	.section	.debug_ranges,"",@progbits
.Ldebug_ranges0:
	.quad	.LBB55-.Ltext0
	.quad	.LBE55-.Ltext0
	.quad	.LBB62-.Ltext0
	.quad	.LBE62-.Ltext0
	.quad	0
	.quad	0
	.section	.debug_line,"",@progbits
.Ldebug_line0:
	.section	.debug_str,"MS",@progbits,1
.LASF127:
	.string	"htable"
.LASF85:
	.string	"last"
.LASF33:
	.string	"_shortbuf"
.LASF157:
	.string	"_IO_lock_t"
.LASF116:
	.string	"stderr"
.LASF22:
	.string	"_IO_buf_end"
.LASF139:
	.string	"__builtin_strcpy"
.LASF110:
	.string	"find_fun_index"
.LASF119:
	.string	"mcnt"
.LASF131:
	.string	"find_ele_fun_set"
.LASF100:
	.string	"bindex"
.LASF20:
	.string	"_IO_write_end"
.LASF3:
	.string	"unsigned int"
.LASF55:
	.string	"next"
.LASF113:
	.string	"skipchar"
.LASF14:
	.string	"_flags"
.LASF26:
	.string	"_markers"
.LASF138:
	.string	"__builtin_fwrite"
.LASF91:
	.string	"lower_fun"
.LASF67:
	.string	"h_mod"
.LASF108:
	.string	"hash_fun_index"
.LASF134:
	.string	"calloc"
.LASF121:
	.string	"mstring"
.LASF58:
	.string	"find_ele_fun_t"
.LASF47:
	.string	"_pos"
.LASF62:
	.string	"strcpy"
.LASF115:
	.string	"stdout"
.LASF25:
	.string	"_IO_save_end"
.LASF87:
	.string	"quick"
.LASF136:
	.string	"malloc"
.LASF93:
	.string	"index"
.LASF79:
	.string	"result"
.LASF50:
	.string	"long long unsigned int"
.LASF51:
	.string	"lower_fun_t"
.LASF24:
	.string	"_IO_backup_base"
.LASF35:
	.string	"_offset"
.LASF96:
	.string	"get_token"
.LASF49:
	.string	"clock_t"
.LASF63:
	.string	"__dest"
.LASF61:
	.string	"fprintf"
.LASF28:
	.string	"_fileno"
.LASF103:
	.string	"word_freq"
.LASF82:
	.string	"find_ele_rec"
.LASF154:
	.string	"GNU C11 5.4.0 20160609 -msse4 -mfpmath=sse -m64 -mtune=generic -march=x86-64 -g -O1 -fno-inline -fno-optimize-sibling-calls -fstack-protector-strong"
.LASF7:
	.string	"size_t"
.LASF150:
	.string	"show_options"
.LASF17:
	.string	"_IO_read_base"
.LASF106:
	.string	"argc"
.LASF114:
	.string	"stdin"
.LASF73:
	.string	"ele1"
.LASF74:
	.string	"ele2"
.LASF45:
	.string	"_next"
.LASF142:
	.string	"free"
.LASF54:
	.string	"freq"
.LASF60:
	.string	"__fmt"
.LASF152:
	.string	"clock"
.LASF86:
	.string	"sort_words"
.LASF147:
	.string	"add_int_option"
.LASF112:
	.string	"infile"
.LASF59:
	.string	"__stream"
.LASF120:
	.string	"scnt"
.LASF12:
	.string	"char"
.LASF146:
	.string	"__fprintf_chk"
.LASF41:
	.string	"_mode"
.LASF77:
	.string	"lower1"
.LASF78:
	.string	"lower2"
.LASF44:
	.string	"_IO_marker"
.LASF141:
	.string	"qsort"
.LASF15:
	.string	"_IO_read_ptr"
.LASF135:
	.string	"exit"
.LASF122:
	.string	"llen"
.LASF140:
	.string	"strcmp"
.LASF101:
	.string	"sbuf"
.LASF130:
	.string	"hash_fun_names"
.LASF124:
	.string	"lcnt"
.LASF18:
	.string	"_IO_write_base"
.LASF159:
	.string	"new_ele"
.LASF125:
	.string	"lower_fun_set"
.LASF48:
	.string	"long long int"
.LASF66:
	.string	"printf"
.LASF71:
	.string	"vele1"
.LASF72:
	.string	"vele2"
.LASF23:
	.string	"_IO_save_base"
.LASF69:
	.string	"h_xor"
.LASF155:
	.string	"dictionary.c"
.LASF128:
	.string	"tsize"
.LASF145:
	.string	"__printf_chk"
.LASF109:
	.string	"lower_fun_index"
.LASF52:
	.string	"HELE"
.LASF98:
	.string	"token_buf"
.LASF89:
	.string	"insert_string"
.LASF75:
	.string	"size"
.LASF118:
	.string	"ucnt"
.LASF36:
	.string	"__pad1"
.LASF37:
	.string	"__pad2"
.LASF38:
	.string	"__pad3"
.LASF39:
	.string	"__pad4"
.LASF40:
	.string	"__pad5"
.LASF83:
	.string	"find_ele_iter_f"
.LASF11:
	.string	"__clock_t"
.LASF111:
	.string	"fname"
.LASF84:
	.string	"find_ele_iter_r"
.LASF151:
	.string	"fopen"
.LASF32:
	.string	"_vtable_offset"
.LASF95:
	.string	"get_word"
.LASF92:
	.string	"find_ele_fun"
.LASF123:
	.string	"lstring"
.LASF65:
	.string	"fgets"
.LASF88:
	.string	"array"
.LASF107:
	.string	"argv"
.LASF133:
	.string	"bufvalid"
.LASF68:
	.string	"h_add"
.LASF126:
	.string	"lower_fun_names"
.LASF76:
	.string	"Strlen"
.LASF56:
	.string	"h_ptr"
.LASF16:
	.string	"_IO_read_end"
.LASF5:
	.string	"short int"
.LASF6:
	.string	"long int"
.LASF80:
	.string	"wlen"
.LASF160:
	.string	"__stack_chk_fail"
.LASF158:
	.string	"new_table"
.LASF117:
	.string	"wcnt"
.LASF53:
	.string	"word"
.LASF153:
	.string	"fclose"
.LASF97:
	.string	"ngram"
.LASF148:
	.string	"add_string_option"
.LASF90:
	.string	"hash_fun"
.LASF81:
	.string	"save_string"
.LASF143:
	.string	"__fgets_alias"
.LASF34:
	.string	"_lock"
.LASF94:
	.string	"init_token"
.LASF10:
	.string	"sizetype"
.LASF0:
	.string	"long unsigned int"
.LASF30:
	.string	"_old_offset"
.LASF43:
	.string	"_IO_FILE"
.LASF132:
	.string	"find_ele_fun_names"
.LASF1:
	.string	"unsigned char"
.LASF102:
	.string	"nextpos"
.LASF46:
	.string	"_sbuf"
.LASF64:
	.string	"__src"
.LASF19:
	.string	"_IO_write_ptr"
.LASF99:
	.string	"first"
.LASF144:
	.string	"strtok"
.LASF156:
	.string	"/nfs/student/student/a/agalczak/cs341/lab3"
.LASF137:
	.string	"fwrite"
.LASF8:
	.string	"__off_t"
.LASF104:
	.string	"verbose"
.LASF149:
	.string	"parse_options"
.LASF4:
	.string	"signed char"
.LASF2:
	.string	"short unsigned int"
.LASF105:
	.string	"main"
.LASF70:
	.string	"compare_ele"
.LASF57:
	.string	"hash_fun_t"
.LASF27:
	.string	"_chain"
.LASF13:
	.string	"FILE"
.LASF129:
	.string	"hash_fun_set"
.LASF29:
	.string	"_flags2"
.LASF31:
	.string	"_cur_column"
.LASF9:
	.string	"__off64_t"
.LASF42:
	.string	"_unused2"
.LASF21:
	.string	"_IO_buf_base"
	.ident	"GCC: (Ubuntu 5.4.0-6ubuntu1~16.04.9) 5.4.0 20160609"
	.section	.note.GNU-stack,"",@progbits
