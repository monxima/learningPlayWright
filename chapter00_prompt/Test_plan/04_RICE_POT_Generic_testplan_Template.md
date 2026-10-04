# RICEPOT Prompt Template for QA Tasks

Oct 4, 2026 · @mooyong

## How to use

Copy the template below, replace every `{{PLACEHOLDER}}`, and delete any line that does not apply. The anti-hallucination rules in Instructions and the Working process block stay the same for every task.

| Letter | Section | Question it answers |
| --- | --- | --- |
| R | Role | Who is the AI acting as? |
| I | Instructions | What is the task, and what are the do and don't rules? |
| C | Context | What product, feature, scope and source material is this about? |
| E | Example | What does a good output look like? |
| P | Parameters | What limits, standards and conventions apply? |
| O | Output | What file, format and sections are delivered? |
| T | Tone | How should it read? |

1. Pick the task type and copy its preset values from the presets table.
2. Fill Context from real sources only: requirements, user stories, screenshots, API specs. Leave unknowns out rather than guessing.
3. Paste the prompt, review the plan the AI returns, then approve it before it creates the deliverable.

## The template

One prompt covers any QA deliverable; only the placeholders change.

```
# R - ROLE
You are a {{ROLE_TITLE}} with {{YEARS}}+ years of experience.
You have a strong understanding of {{DOMAINS}}.
You are skilled at {{CORE_STRENGTHS}} and produce QA documentation to enterprise standard.
Your job is to create a {{DELIVERABLE}} for an enterprise-level product.

# I - INSTRUCTIONS
1. Task: {{TASK_VERB}} a {{DELIVERABLE}} for {{FEATURE_OR_SCOPE}} of {{PRODUCT_NAME}}.
2. [Don't] Do not invent features, APIs, error codes, UI elements, or behavior.
3. [Don't] Do not assume default or "typical" system behavior.
4. [Do] Use only the information given in Context and the attached sources.
5. [Do] If information is missing or unclear, respond with exactly:
   "Insufficient information to determine."
6. [Do] If a detail is inferred, label it explicitly as:
   "Inference (low confidence)"
7. [Do] Output must be deterministic and repeatable: same input gives the same structure, IDs and order.
8. {{EXTRA_RULES}}

# C - CONTEXT
Product: {{PRODUCT_NAME}} - {{PRODUCT_DESCRIPTION}}
Feature under test: {{FEATURE_DESCRIPTION}}
In scope: {{IN_SCOPE}}
Out of scope: {{OUT_OF_SCOPE}}
Source material: {{SOURCES}}
Environments: {{ENVIRONMENTS}}
Constraints: {{CONSTRAINTS}}

# E - EXAMPLE
Follow the structure and level of detail of this sample. Do not copy its content.
{{EXAMPLE}}

# P - PARAMETERS
- Test types: {{TEST_TYPES}}
- Test design techniques: {{TECHNIQUES}}
- Priority scale: {{PRIORITY_SCALE}}
- ID format: {{ID_FORMAT}}
- Standard or house template: {{STANDARD}}
- Size limit: {{SIZE_LIMIT}}
- Language: {{LANGUAGE}}

# O - OUTPUT
- Format: {{OUTPUT_FORMAT}}
- File name: {{FILE_NAME}}
- Location: {{OUTPUT_LOCATION}}
- Sections, in this order: {{SECTIONS}}
- Final section: "Open questions", listing every item marked
  "Insufficient information to determine."

# T - TONE
{{TONE}}

# WORKING PROCESS
1. Plan first. Show me exactly what you are going to create: the outline, the sections, and the inputs you will use.
2. Ask me about anything missing or unclear before you start.
3. Wait for my approval of the plan. Do not create the deliverable before I approve.
4. Then create it step by step, explaining what you are doing at each step.
```

## Placeholder reference

Every placeholder, what goes in it, and a sample value.

| Placeholder | Section | What to put | Sample value |
| --- | --- | --- | --- |
| `{{ROLE_TITLE}}` | R | Seniority and role the AI plays | QA Lead |
| `{{YEARS}}` | R | Years of experience | 15 |
| `{{DOMAINS}}` | R | Industries or platforms the role knows | IT and CRM projects such as Salesforce |
| `{{CORE_STRENGTHS}}` | R | Skills that matter for this task | QA documentation, test strategy |
| `{{TASK_VERB}}` | I | Generate, review, analyze, update | Generate |
| `{{DELIVERABLE}}` | R, I | The artifact you want | test plan |
| `{{FEATURE_OR_SCOPE}}` | I | Feature, module or release | the login page |
| `{{PRODUCT_NAME}}` | I, C | Product or system under test | VWO.com |
| `{{EXTRA_RULES}}` | I | Task-specific rules; delete if none | One expected result per test step |
| `{{PRODUCT_DESCRIPTION}}` | C | One line on what the product does | A/B testing website |
| `{{FEATURE_DESCRIPTION}}` | C | Fields, buttons and behavior you know for certain | Email, password, submit button, remember me |
| `{{IN_SCOPE}}` | C | What this deliverable covers | Valid and invalid login |
| `{{OUT_OF_SCOPE}}` | C | What it must not cover | Sign-up, SSO |
| `{{SOURCES}}` | C | Attached or pasted material | PRD, user stories, screenshots |
| `{{ENVIRONMENTS}}` | C | Where testing runs | Staging; Chrome and Firefox |
| `{{CONSTRAINTS}}` | C | Deadlines, tools, access limits | No production data |
| `{{EXAMPLE}}` | E | A sample row, section or full document | One test case row in your house format |
| `{{TEST_TYPES}}` | P | Types of testing to include | Functional, negative, UI |
| `{{TECHNIQUES}}` | P | Design techniques to apply | Equivalence partitioning, boundary values |
| `{{PRIORITY_SCALE}}` | P | Priority or severity levels | P1 to P4 |
| `{{ID_FORMAT}}` | P | Naming pattern for IDs | TC-LOGIN-001 |
| `{{STANDARD}}` | P | Standard or template to follow | ISO/IEC/IEEE 29119-3 |
| `{{SIZE_LIMIT}}` | P | Maximum length or count | 30 test cases |
| `{{LANGUAGE}}` | P | Output language | English |
| `{{OUTPUT_FORMAT}}` | O | File type | Markdown |
| `{{FILE_NAME}}` | O | Exact file name | testplan.md |
| `{{OUTPUT_LOCATION}}` | O | Folder or destination | This folder |
| `{{SECTIONS}}` | O | Section list, in order | See the presets table |
| `{{TONE}}` | T | How it should read | Technical, precise, enterprise-grade, concise |

## Presets by QA task type

Swap these four values to switch the template between tasks; everything else stays the same.

| Task | `{{DELIVERABLE}}` | `{{FILE_NAME}}` | `{{SECTIONS}}` | Parameters that matter most |
| --- | --- | --- | --- | --- |
| Test plan | test plan | testplan.md | Objective, Scope, Out of scope, Test approach, Test types, Entry and exit criteria, Environment, Test data, Roles, Schedule, Risks and mitigations, Deliverables | Standard, test types |
| Test cases | test case suite | testcases.md | Table with ID, Title, Preconditions, Steps, Test data, Expected result, Priority, Type | ID format, techniques, priority scale, size limit |
| Test scenarios | test scenario list | scenarios.md | Table with ID, Scenario, Requirement reference, Priority | ID format, test types |
| Bug report | bug report | bug-report.md | Summary, Environment, Steps to reproduce, Actual result, Expected result, Severity, Priority, Evidence | Priority scale, severity scale |
| Requirements analysis | requirements analysis | requirements-analysis.md | Requirement summary, Ambiguities, Missing information, Testability issues, Questions for the product owner | Sources, size limit |
| Test summary report | test summary report | test-summary.md | Scope tested, Results by status, Defects by severity, Coverage gaps, Risks, Release recommendation | Sources, standard |

## Worked example: VWO login test plan

Your original prompt, rewritten in the template. Example and Parameters were blank in the original, so Example is marked as not provided and the Parameters shown are suggested defaults to adjust.

```
# R - ROLE
You are a QA Lead with 15+ years of experience.
You have a strong understanding of IT and CRM projects such as Salesforce.
You are skilled at QA documentation and produce it to enterprise standard.
Your job is to create a test plan for an enterprise-level product.

# I - INSTRUCTIONS
1. Task: Generate a test plan for the login page of VWO.com.
2. [Don't] Do not invent features, APIs, error codes, UI elements, or behavior.
3. [Don't] Do not assume default or "typical" system behavior.
4. [Do] Use only the information given in Context and the attached sources.
5. [Do] If information is missing or unclear, respond with exactly:
   "Insufficient information to determine."
6. [Do] If a detail is inferred, label it explicitly as:
   "Inference (low confidence)"
7. [Do] Output must be deterministic and repeatable: same input gives the same structure, IDs and order.

# C - CONTEXT
Product: VWO.com - an A/B testing website.
Feature under test: the login page, which has an email field, a password field, a submit button, and a remember me option.
In scope: valid login and invalid login.
Out of scope: anything not listed in this Context.
Source material: this prompt only.

# E - EXAMPLE
No example provided. Use the section list in Output.

# P - PARAMETERS
- Test types: functional, negative, UI
- Priority scale: P1 to P4
- ID format: TP-LOGIN-001
- Language: English

# O - OUTPUT
- Format: Markdown
- File name: testplan.md
- Location: this folder
- Sections, in this order: Objective, Scope, Out of scope, Test approach, Test types, Entry and exit criteria, Environment, Test data, Roles, Schedule, Risks and mitigations, Deliverables
- Final section: "Open questions", listing every item marked
  "Insufficient information to determine."

# T - TONE
Technical, precise, enterprise-grade, concise.

# WORKING PROCESS
1. Plan first. Show me exactly what you are going to create: the outline, the sections, and the inputs you will use.
2. Ask me about anything missing or unclear before you start.
3. Wait for my approval of the plan. Do not create the deliverable before I approve.
4. Then create it step by step, explaining what you are doing at each step.
```
